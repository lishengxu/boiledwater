package com.deepindex.attributeset.listparser;

import android.util.Log;

import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class StringListParser extends ListParser<String> {
    public StringListParser(AttributeParser attributeParser) {
        super(attributeParser);
    }

    @Override
    public ArrayList<String> parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        return parseList(key, type, xmlPullParser);
    }

    @Override
    protected String parseSubItem(String key, String type, XmlPullParser xmlPullParser)
            throws IOException, XmlPullParserException {
        String value = xmlPullParser.getAttributeValue(null, SUB_VALUE);
        if (value == null) {
            Log.w(LOG_TAG, SUB_VALUE + " is not exist with key:" + key + ", type:" + type);
        }

        return value;
    }
}
