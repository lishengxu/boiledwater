package com.deepindex.attributeset.objectparser;

import android.util.Log;

import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class StringObjectParser extends ObjectParser<String> {
    public StringObjectParser(AttributeParser attributeParser) {
        super(attributeParser);
    }

    @Override
    public String parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        return parseObject(key, type, xmlPullParser);
    }

    @Override
    protected String parseObject(String key, String type, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String value = xmlPullParser.getAttributeValue(null, VALUE);
        if (value == null) {
            Log.w(LOG_TAG, VALUE + " is not exist with key:" + key + ", type:" + type);
        }

        return value;
    }
}
