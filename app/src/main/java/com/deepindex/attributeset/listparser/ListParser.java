package com.deepindex.attributeset.listparser;

import android.util.Log;

import com.deepindex.attributeset.BaseParser;
import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public abstract class ListParser<T> extends BaseParser<ArrayList<T>> {
    private static final int ATTRIBUTE_COUNT = 2;
    protected static final String TAG_SUB_NAME = "sub";
    protected static final String SUB_VALUE = "value";

    public ListParser(AttributeParser attributeParser) {
        super(attributeParser);
    }

    @Override
    public boolean checkAttributeCount(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        int attributeCount = xmlPullParser.getAttributeCount();
        if (attributeCount != ATTRIBUTE_COUNT) {
            Log.w(LOG_TAG, "attribute count is " + attributeCount
                    + " not equals to " + ATTRIBUTE_COUNT + " with key:" + key + ", type:" + type);
            return false;
        }
        return true;
    }

    protected ArrayList<T> parseList(String key, String type, XmlPullParser xmlPullParser)
            throws IOException, XmlPullParserException {
        int eventType;
        do {
            eventType = xmlPullParser.next();
        } while ((eventType != XmlPullParser.START_TAG) && (eventType != XmlPullParser.END_TAG));

        if (eventType == XmlPullParser.END_TAG) {
            return new ArrayList<>();
        }
        ArrayList<T> result = new ArrayList<>();
        boolean parseSuccess = true;
        while (eventType != XmlPullParser.END_TAG) {
            if (eventType == XmlPullParser.START_TAG) {
                T sub = null;
                String name = xmlPullParser.getName();
                if (TAG_SUB_NAME.equals(name)) {
                    sub = parseSubItem(key, type, xmlPullParser);
                }

                eventType = xmlPullParser.getEventType();
                while (eventType != XmlPullParser.END_TAG) {
                    eventType = xmlPullParser.next();
                }

                if (sub != null) {
                    result.add(sub);
                } else {
                    parseSuccess = false;
                }
            }
            eventType = xmlPullParser.next();
        }
        if (parseSuccess) {
            return result;
        }
        Log.w(LOG_TAG, "parse fail with key:" + key);
        return null;
    }

    protected abstract T parseSubItem(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException;
}
