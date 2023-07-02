package com.deepindex.attributeset.objectparser;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import com.deepindex.attributeset.BaseParser;
import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Map;

public class ObjectAttributeParser extends BaseParser<Object> {
    private final Map<String, BaseParser<?>> mBaseObjectParsers;
    private final BaseParser<?> mAttributeObjectParser;
    private String mTagName = "attribute";

    public ObjectAttributeParser(AttributeParser attributeParser) {
        super(attributeParser);

        mBaseObjectParsers = new ArrayMap<>();
        mBaseObjectParsers.put("boolean", new BooleanObjectParser(mAttributeParser));
        mBaseObjectParsers.put("byte", new ByteObjectParser(mAttributeParser));
        mBaseObjectParsers.put("short", new ShortObjectParser(mAttributeParser));
        mBaseObjectParsers.put("int", new IntObjectParser(mAttributeParser));
        mBaseObjectParsers.put("long", new LongObjectParser(mAttributeParser));
        mBaseObjectParsers.put("float", new FloatObjectParser(mAttributeParser));
        mBaseObjectParsers.put("double", new DoubleObjectParser(mAttributeParser));
        mBaseObjectParsers.put("char", new CharObjectParser(mAttributeParser));
        mBaseObjectParsers.put("string", new StringObjectParser(mAttributeParser));
        mAttributeObjectParser = new AttributeObjectParser(mAttributeParser);
    }

    @Override
    public void addSkipAttributeName(String attributeName) {
        mAttributeObjectParser.addSkipAttributeName(attributeName);
    }

    @Override
    public Object parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        if (TextUtils.isEmpty(key)) {
            Log.w(LOG_TAG, "key is empty");
            return null;
        }

        if (TextUtils.isEmpty(type)) {
            Log.w(LOG_TAG, "type is empty with key:" + key);
            return null;
        }

        if (xmlPullParser == null) {
            Log.w(LOG_TAG, "xml parser is null with key:" + key + ", type:" + type);
            return null;
        }

        Object result = null;
        BaseParser<?> objectParser = mBaseObjectParsers.get(type.toLowerCase());
        if (objectParser != null) {
            if (objectParser.checkAttributeCount(key, type, xmlPullParser)) {
                result = objectParser.parse(key, type, xmlPullParser);
                int eventType;
                do {
                    eventType = xmlPullParser.next();
                } while (eventType != XmlPullParser.END_TAG);
            }
        } else {
            result = mAttributeObjectParser.parse(key, type, xmlPullParser);
        }
        return result;
    }

    public String getTagName() {
        return mTagName;
    }

    public void setTagName(String tagName) {
        if (TextUtils.isEmpty(tagName)) {
            return;
        }
        mTagName = tagName;
    }
}
