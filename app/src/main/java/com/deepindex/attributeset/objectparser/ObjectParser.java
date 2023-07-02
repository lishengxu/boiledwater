package com.deepindex.attributeset.objectparser;

import android.util.Log;

import com.deepindex.attributeset.BaseParser;
import com.deepindex.attributeset.AttributeParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public abstract class ObjectParser<T> extends BaseParser<T> {
    protected static final String VALUE = "value";
    private static final int ATTRIBUTE_COUNT = 3;

    public ObjectParser(AttributeParser attributeParser) {
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

    protected abstract T parseObject(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException;
}
