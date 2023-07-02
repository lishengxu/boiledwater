package com.deepindex.attributeset.mapparser;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import com.deepindex.attributeset.BaseParser;
import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Map;

public class MapAttributeParser extends BaseParser<Object> {
    private final Map<String, BaseParser<?>> mBaseMapParsers;
    private final BaseParser<?> mAttributeMapParser;
    private String mTagName = "attribute_map";

    public MapAttributeParser(AttributeParser attributeParser) {
        super(attributeParser);

        mBaseMapParsers = new ArrayMap<>();
        mBaseMapParsers.put("boolean", new BooleanMapParser(mAttributeParser));
        mBaseMapParsers.put("byte", new ByteMapParser(mAttributeParser));
        mBaseMapParsers.put("short", new ShortMapParser(mAttributeParser));
        mBaseMapParsers.put("int", new IntMapParser(mAttributeParser));
        mBaseMapParsers.put("long", new LongMapParser(mAttributeParser));
        mBaseMapParsers.put("float", new FloatMapParser(mAttributeParser));
        mBaseMapParsers.put("double", new DoubleMapParser(mAttributeParser));
        mBaseMapParsers.put("char", new CharMapParser(mAttributeParser));
        mBaseMapParsers.put("string", new StringMapParser(mAttributeParser));
        mAttributeMapParser = new AttributeMapParser(mAttributeParser);
    }

    @Override
    public void addSkipAttributeName(String attributeName) {
        mAttributeMapParser.addSkipAttributeName(attributeName);
    }

    @Override
    public Object parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        if (TextUtils.isEmpty(key)) {
            Log.w(LOG_TAG, "attribute key is empty");
            return null;
        }

        if (TextUtils.isEmpty(type)) {
            Log.w(LOG_TAG, "attribute type is empty with key:" + key);
            return null;
        }

        if (xmlPullParser == null) {
            Log.w(LOG_TAG, "xml parser is null with key:" + key + ", type:" + type);
            return null;
        }

        Object result = null;
        BaseParser<?> mapParser = mBaseMapParsers.get(type.toLowerCase());
        if (mapParser != null) {
            if (mapParser.checkAttributeCount(key, type, xmlPullParser)) {
                result = mapParser.parse(key, type, xmlPullParser);
            }
        } else {
            result = mAttributeMapParser.parse(key, type, xmlPullParser);
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
