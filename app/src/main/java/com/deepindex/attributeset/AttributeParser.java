package com.deepindex.attributeset;

import android.content.Context;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.deepindex.attributeset.listparser.ListAttributeParser;
import com.deepindex.attributeset.mapparser.MapAttributeParser;
import com.deepindex.attributeset.objectparser.ObjectAttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class AttributeParser {
    public static final String LOG_TAG = "AttributeParser";
    public static final String KEY = "key";
    public static final String TYPE = "type";
    public static final String FUNCTION_NAME = "function_name";
    private final Context mContext;
    private AttributeCollection mAttributeCollection;
    private String mAttributeClassPath = "attributepath";
    private String mPreMethodName = "set";
    private final Map<String, BaseParser<?>> mParsers;

    public AttributeParser(Context context) {
        this(context, new AttributeCollection());
    }

    public AttributeParser(Context context, AttributeCollection attributeCollection) {
        mContext = context.getApplicationContext();
        mAttributeCollection = attributeCollection;

        mParsers = new ArrayMap<>();
        ObjectAttributeParser objectAttributeParser = new ObjectAttributeParser(this);
        objectAttributeParser.addSkipAttributeName(AttributeParser.KEY);
        objectAttributeParser.addSkipAttributeName(AttributeParser.TYPE);
        mParsers.put(objectAttributeParser.getTagName(), objectAttributeParser);

        ListAttributeParser listAttributeParser = new ListAttributeParser(this);
        listAttributeParser.addSkipAttributeName(AttributeParser.KEY);
        listAttributeParser.addSkipAttributeName(AttributeParser.TYPE);
        mParsers.put(listAttributeParser.getTagName(), listAttributeParser);

        MapAttributeParser mapAttributeParser = new MapAttributeParser(this);
        mapAttributeParser.addSkipAttributeName(AttributeParser.KEY);
        mapAttributeParser.addSkipAttributeName(AttributeParser.TYPE);
        mParsers.put(mapAttributeParser.getTagName(), mapAttributeParser);
    }

    /**
     * Get the classpath where the attribute class is placed,
     * default is {{@link android.content.Context#getPackageName} + ".attributepath"}.
     *
     * @return classpath.
     */
    public String getAttributeClassPath() {
        return mContext.getPackageName() + "." + mAttributeClassPath;
    }

    /**
     * Set the classpath where the attribute class is placed.
     *
     * @param path classpath.
     */
    public void setAttributeClassPath(@NonNull String path) {
        if (TextUtils.isEmpty(path)) {
            Log.w(LOG_TAG, "not to set attribute class path with null");
            return;
        }
        Log.w(LOG_TAG, "attribute class path is change from " + mAttributeClassPath + " to " + path);
        mAttributeClassPath = path;
    }

    /**
     * Get the function prefix string in the attribute class that matches the attribute name in the attribute xml file,
     * eg: the function name is "setName" matches the attribute name "name".
     * default is "set"
     *
     * @return pre method name.
     */
    public String getPreMethodName() {
        return mPreMethodName;
    }

    /**
     * Set the function prefix string in the attribute class that matches the attribute name in the attribute xml file.
     * @param preMethodName pre method name.
     */
    public void setPreMethodName(@NonNull String preMethodName) {
        if (TextUtils.isEmpty(preMethodName)) {
            Log.w(LOG_TAG, "not to set pre method name with null");
            return;
        }
        Log.w(LOG_TAG, "pre method name is change from "
            + mPreMethodName + " to " + preMethodName);
        mPreMethodName = preMethodName;
    }

    public AttributeCollection getAttributeCollection() {
        return mAttributeCollection;
    }

    public void setAttributeCollection(AttributeCollection attributeCollection) {
        if (attributeCollection == null) {
            Log.w(LOG_TAG, "not to set attribute collection with null");
            return;
        }
        Log.w(LOG_TAG, "set attributeCollection success");
        mAttributeCollection = attributeCollection;
    }

    public boolean parse(InputStream inputStream) throws XmlPullParserException, IOException {
        if (inputStream == null) {
            return false;
        }
        XmlPullParser xmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
        xmlPullParser.setInput(inputStream, "UTF-8");
        return parse(xmlPullParser);
    }

    public boolean parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser == null) {
            return false;
        }
        boolean parseResult;
        int eventType = xmlPullParser.getEventType();
        //int START_DOCUMENT = 0;  开始解析文档
        //int END_DOCUMENT = 1;    内容结束
        //int START_TAG = 2;       读取标签
        //int END_TAG = 3;         标签读取结束
        //int TEXT = 4;            读取文本
        while (eventType != XmlPullParser.END_DOCUMENT) {
            Log.e(LOG_TAG, "eventType:" + eventType);
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    parseResult = parseAttribute(xmlPullParser);
                    if (!parseResult) {
                        return false;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    break;
            }

            eventType = xmlPullParser.next();
        }
        return true;
    }

    public boolean parseAttribute(XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        String key = xmlPullParser.getAttributeValue(null, KEY);
        if (TextUtils.isEmpty(key)) {
            Log.w(AttributeParser.LOG_TAG, KEY + " is empty");
            return true;
        }
        String type = xmlPullParser.getAttributeValue(null, TYPE);
        if (TextUtils.isEmpty(type)) {
            Log.w(AttributeParser.LOG_TAG, TYPE + " is empty with " + KEY + ":" + key);
            return true;
        }
        Log.d(AttributeParser.LOG_TAG,
                "parse attribute:" + AttributeParser.getAttributeInfo(xmlPullParser));
        Object result = null;
        BaseParser<?> parser = mParsers.get(xmlPullParser.getName());
        if (parser != null) {
            result = parser.parse(key, type, xmlPullParser);
        }
        if (result != null) {
            mAttributeCollection.put(key, result);
        }
//        Log.e(LOG_TAG, "name:" + xmlPullParser.getName());
//        Log.e(LOG_TAG, "attribute size:" + xmlPullParser.getAttributeCount());
//        if (xmlPullParser.getAttributeCount() > 0) {
//            Log.e(LOG_TAG, "id:" + xmlPullParser.getAttributeValue(0));
//        }
        return true;
    }

    public static String getAttributeInfo(XmlPullParser parser) {
        int count = parser.getAttributeCount();
        StringBuilder builder = new StringBuilder();
        builder.append(parser.getName());
        for (int i = 0; i < count; ++i) {
            builder.append(" ").append(parser.getAttributeName(i))
                .append("=")
                .append(parser.getAttributeValue(i));
        }
        return builder.toString();
    }

    public static class Attribute {

    }
}
