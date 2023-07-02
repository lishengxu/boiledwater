package com.deepindex.attributeset.listparser;

import android.text.TextUtils;
import android.util.Log;

import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class ByteListParser extends ListParser<Byte> {
    public ByteListParser(AttributeParser attributeParser) {
        super(attributeParser);
    }

    @Override
    public ArrayList<Byte> parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        return parseList(key, type, xmlPullParser);
    }

    @Override
    protected Byte parseSubItem(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        String value = xmlPullParser.getAttributeValue(null, SUB_VALUE);
        if (TextUtils.isEmpty(value)) {
            Log.w(LOG_TAG, SUB_VALUE + " is empty with key:" + key + ", type:" + type);
            return null;
        }

        try {
            return Byte.valueOf(value);
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG, SUB_VALUE + ":" + value + " is not " + type + " with key:" + key, e);
        }
        return null;
    }

}
