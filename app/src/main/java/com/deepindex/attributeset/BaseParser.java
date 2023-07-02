package com.deepindex.attributeset;

import android.text.TextUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The basic parser used to parse the content of the xml file in the specified format.
 *
 */
public abstract class BaseParser<T> {
    protected static final String LOG_TAG = AttributeParser.LOG_TAG;
    protected final AttributeParser mAttributeParser;
    private final List<String> mSkipParseAttributeNames;

    public BaseParser(AttributeParser attributeParser) {
        mAttributeParser = attributeParser;
        mSkipParseAttributeNames = new ArrayList<>();
    }

    public void addSkipAttributeName(String attributeName) {
        if (TextUtils.isEmpty(attributeName)) {
            return;
        }
        mSkipParseAttributeNames.add(attributeName);
    }

    public boolean skipAttributeName(String attributeName) {
        return mSkipParseAttributeNames.contains(attributeName);
    }

    /**
     * to parse the xml content using the specified format.
     * @param key the attribute key
     * @param type the type of information
     * @param xmlPullParser xml data source
     */
    public abstract T parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException;

    /**
     * check the count of attributes in the content of the xml value.
     * @param key the attribute key
     * @param type the type of information
     * @param xmlPullParser xml data source
     * @return true if count is valid, or false if not.
     */
    public boolean checkAttributeCount(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        return true;
    }
}
