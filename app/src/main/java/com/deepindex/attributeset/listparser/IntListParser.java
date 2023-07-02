package com.deepindex.attributeset.listparser;

import android.text.TextUtils;
import android.util.Log;

import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class IntListParser extends ListParser<Integer> {
    public IntListParser(AttributeParser attributeParser) {
        super(attributeParser);
    }

    @Override
    public ArrayList<Integer> parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        return parseList(key, type, xmlPullParser);
    }

    @Override
    protected Integer parseSubItem(String key, String type, XmlPullParser xmlPullParser)
            throws IOException, XmlPullParserException {
        String value = xmlPullParser.getAttributeValue(null, SUB_VALUE);
        if (TextUtils.isEmpty(value)) {
            Log.w(LOG_TAG, SUB_VALUE + " is empty with key:" + key + ", type:" + type);
            return null;
        }

        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG, SUB_VALUE + ":" + value + " is not " + type + " with key:" + key, e);
        }
        return null;
    }

}
