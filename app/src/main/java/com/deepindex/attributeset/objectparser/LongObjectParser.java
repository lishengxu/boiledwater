package com.deepindex.attributeset.objectparser;

import android.text.TextUtils;
import android.util.Log;

import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class LongObjectParser extends ObjectParser<Long> {
    public LongObjectParser(AttributeParser attributeParser) {
        super(attributeParser);
    }

    @Override
    public Long parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        return parseObject(key, type, xmlPullParser);
    }

    @Override
    protected Long parseObject(String key, String type, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String value = xmlPullParser.getAttributeValue(null, VALUE);
        if (TextUtils.isEmpty(value)) {
            Log.w(LOG_TAG, VALUE + " is empty with key:" + key + ", type:" + type);
            return null;
        }

        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG, VALUE + ":" + value + " is not " + type + " with key:" + key, e);
        }
        return null;
    }

}
