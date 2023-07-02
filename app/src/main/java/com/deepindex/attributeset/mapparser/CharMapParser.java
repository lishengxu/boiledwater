package com.deepindex.attributeset.mapparser;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;

import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class CharMapParser extends MapParser<Character> {
    public CharMapParser(AttributeParser attributeParser) {
        super(attributeParser);
    }

    @Override
    public ArrayMap<String, Character> parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        return parseMap(key, type, xmlPullParser);
    }

    @Override
    protected Pair<String, Character> parseSubItem(String key, String type, XmlPullParser xmlPullParser)
            throws IOException, XmlPullParserException {
        String subKey = xmlPullParser.getAttributeValue(null, SUB_KEY);
        if (TextUtils.isEmpty(subKey)) {
            Log.w(LOG_TAG, SUB_KEY + " is empty with key:" + key + ", type:" + type);
            return null;
        }

        String value = xmlPullParser.getAttributeValue(null, SUB_VALUE);
        if (TextUtils.isEmpty(value)) {
            Log.w(LOG_TAG, SUB_VALUE + " is empty with key:" + key + ", type:" + type);
            return null;
        }

        if (value.length() == 1) {
            return Pair.create(subKey, value.charAt(0));
        }
        Log.w(LOG_TAG, SUB_VALUE + ":" + value + " is not " + type + " with key:" + key);
        return null;
    }
}