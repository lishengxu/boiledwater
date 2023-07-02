package com.deepindex.attributeset.listparser;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import com.deepindex.attributeset.BaseParser;
import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Map;

public class ListAttributeParser extends BaseParser<Object> {
    private final Map<String, BaseParser<?>> mBaseListParsers;
    private final BaseParser<?> mAttributeListParser;
    private String mTagName = "attribute_list";

    public ListAttributeParser(AttributeParser attributeParser) {
        super(attributeParser);

        mBaseListParsers = new ArrayMap<>();
        mBaseListParsers.put("boolean", new BooleanListParser(mAttributeParser));
        mBaseListParsers.put("byte", new ByteListParser(mAttributeParser));
        mBaseListParsers.put("short", new ShortListParser(mAttributeParser));
        mBaseListParsers.put("int", new IntListParser(mAttributeParser));
        mBaseListParsers.put("long", new LongListParser(mAttributeParser));
        mBaseListParsers.put("float", new FloatListParser(mAttributeParser));
        mBaseListParsers.put("double", new DoubleListParser(mAttributeParser));
        mBaseListParsers.put("char", new CharListParser(mAttributeParser));
        mBaseListParsers.put("string", new StringListParser(mAttributeParser));
        mAttributeListParser = new AttributeListParser(mAttributeParser);
    }

    @Override
    public void addSkipAttributeName(String attributeName) {
        mAttributeListParser.addSkipAttributeName(attributeName);
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
        BaseParser<?> listParser = mBaseListParsers.get(type.toLowerCase());
        if (listParser != null) {
            if (listParser.checkAttributeCount(key, type, xmlPullParser)) {
                result = listParser.parse(key, type, xmlPullParser);
            }
        } else {
            result = mAttributeListParser.parse(key, type, xmlPullParser);
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
