package com.deepindex.attributeset.mapparser;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;

import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class StringMapParser extends MapParser<String> {
    public StringMapParser(AttributeParser attributeParser) {
        super(attributeParser);
    }

    @Override
    public ArrayMap<String, String> parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        return parseMap(key, type, xmlPullParser);
    }

    @Override
    protected Pair<String, String> parseSubItem(String key, String type, XmlPullParser xmlPullParser)
            throws IOException, XmlPullParserException {
        String subKey = xmlPullParser.getAttributeValue(null, SUB_KEY);
        if (TextUtils.isEmpty(subKey)) {
            Log.w(LOG_TAG, SUB_KEY + " is empty with key:" + key + ", type:" + type);
            return null;
        }

        String value = xmlPullParser.getAttributeValue(null, SUB_VALUE);
        if (value == null) {
            Log.w(LOG_TAG, SUB_VALUE + " is not exist with key:" + key + ", type:" + type);
            return null;
        }

        return Pair.create(subKey, value);
    }
}
