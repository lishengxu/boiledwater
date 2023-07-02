package com.deepindex.attributeset.listparser;

import android.text.TextUtils;
import android.util.Log;

import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class BooleanListParser extends ListParser<Boolean> {

    private static final String TRUE = "true";
    private static final String FALSE = "false";

    public BooleanListParser(AttributeParser attributeParser) {
        super(attributeParser);
    }

    @Override
    public ArrayList<Boolean> parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        return parseList(key, type, xmlPullParser);
    }

    @Override
    protected Boolean parseSubItem(String key, String type, XmlPullParser xmlPullParser)
            throws IOException, XmlPullParserException {
        String value = xmlPullParser.getAttributeValue(null, SUB_VALUE);
        if (TextUtils.isEmpty(value)) {
            Log.w(LOG_TAG, SUB_VALUE + " is empty with key:" + key + ", type:" + type);
            return null;
        }

        if (TRUE.equalsIgnoreCase(value) || FALSE.equalsIgnoreCase(value)) {
            return Boolean.valueOf(value);
        }
        Log.w(LOG_TAG, SUB_VALUE + ":" + value + " is not " + type + " with key:" + key);
        return null;
    }

}
