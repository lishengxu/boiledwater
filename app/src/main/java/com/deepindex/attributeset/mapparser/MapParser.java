package com.deepindex.attributeset.mapparser;

import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;

import com.deepindex.attributeset.BaseParser;
import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public abstract class MapParser<T> extends BaseParser<ArrayMap<String, T>> {
    private static final int ATTRIBUTE_COUNT = 2;
    protected static final String TAG_SUB_NAME = "sub";
    protected static final String SUB_KEY = "key";
    protected static final String SUB_VALUE = "value";

    public MapParser(AttributeParser attributeParser) {
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

    protected ArrayMap<String, T> parseMap(String key, String type, XmlPullParser xmlPullParser)
            throws IOException, XmlPullParserException {
        int eventType;
        do {
            eventType = xmlPullParser.next();
        } while ((eventType != XmlPullParser.START_TAG) && (eventType != XmlPullParser.END_TAG));
        if (eventType == XmlPullParser.END_TAG) {
            return new ArrayMap<>();
        }
        ArrayMap<String, T> result = new ArrayMap<>();
        boolean parseSuccess = true;
        while (eventType != XmlPullParser.END_TAG) {
            if (eventType == XmlPullParser.START_TAG) {
                Pair<String, T> sub = null;
                String name = xmlPullParser.getName();
                if (TAG_SUB_NAME.equals(name)) {
                    sub = parseSubItem(key, type, xmlPullParser);
                }

                eventType = xmlPullParser.getEventType();
                while (eventType != XmlPullParser.END_TAG) {
                    eventType = xmlPullParser.next();
                }

                if (sub != null) {
                    result.put(sub.first, sub.second);
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

    protected abstract Pair<String, T> parseSubItem(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException;
}
