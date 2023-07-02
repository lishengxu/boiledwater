package com.deepindex.attributeset.mapparser;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;

import com.deepindex.attributeset.AttributeParseHelper;
import com.deepindex.attributeset.AttributeParser;
import com.deepindex.attributeset.listparser.AttributeListParser;
import com.deepindex.attributeset.objectparser.AttributeObjectParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AttributeMapParser extends MapParser<AttributeParser.Attribute> {
    private static final int ATTRIBUTE_COUNT = 2;
    private final AttributeParseHelper mAttributeParseHelper;

    public AttributeMapParser(AttributeParser attributeParser) {
        super(attributeParser);
        mAttributeParseHelper = new AttributeParseHelper(mAttributeParser, this);
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

    @Override
    public ArrayMap<String, AttributeParser.Attribute> parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        if (!checkAttributeCount(key, type, xmlPullParser)) {
            return null;
        }

        return parseMap(key, type, xmlPullParser);
    }

    @Override
    protected Pair<String, AttributeParser.Attribute> parseSubItem(String key, String type, XmlPullParser xmlPullParser)
            throws IOException, XmlPullParserException {
        String subKey = xmlPullParser.getAttributeValue(null, SUB_KEY);
        if (TextUtils.isEmpty(subKey)) {
            Log.w(LOG_TAG, SUB_KEY + " is empty with key:" + key + ", type:" + type);
            return null;
        }

        AttributeParser.Attribute attribute = mAttributeParseHelper.parseAttribute(key, type, xmlPullParser);

        int eventType;
        do {
            eventType = xmlPullParser.next();
        } while ((eventType != XmlPullParser.START_TAG) && (eventType != XmlPullParser.END_TAG));

        if (eventType == XmlPullParser.END_TAG) {
            if (attribute == null) {
                Log.w(LOG_TAG, "attribute is invalid with key:" + key + ", type:" + type);
                return null;
            }
            return Pair.create(subKey, attribute);
        }

        if (attribute == null) {
            //skip attribute stack
            Log.w(LOG_TAG, "attribute is invalid with key:" + key + ", type:" + type);
            skipInvalidAttributeXml(key, type, xmlPullParser);
            return null;
        }

        boolean parseSuccess = true;

        ArrayList<Pair<String, Object>> functionInfoList = new ArrayList<>();
        while (eventType != XmlPullParser.END_TAG) {
            if (eventType == XmlPullParser.START_TAG) {

                String functionName = xmlPullParser.getAttributeValue(null, AttributeParser.FUNCTION_NAME);
                if (TextUtils.isEmpty(functionName)) {
                    Log.w(AttributeParser.LOG_TAG, AttributeParser.FUNCTION_NAME + " is empty");
                    eventType = xmlPullParser.next();
                    parseSuccess = false;
                    continue;
                }
                String subAttributeType = xmlPullParser.getAttributeValue(null, AttributeParser.TYPE);
                if (TextUtils.isEmpty(subAttributeType)) {
                    Log.w(AttributeParser.LOG_TAG, AttributeParser.TYPE + " is empty with "
                            + AttributeParser.FUNCTION_NAME + ":" + functionName);
                    eventType = xmlPullParser.next();
                    parseSuccess = false;
                    continue;
                }
                Object sub = null;
                String name = xmlPullParser.getName();
                if ("attribute".equals(name)) {
                    AttributeObjectParser attributeObjectParser = new AttributeObjectParser(mAttributeParser);
                    attributeObjectParser.addSkipAttributeName(AttributeParser.FUNCTION_NAME);
                    sub = attributeObjectParser.parse(functionName, subAttributeType, xmlPullParser);
                } else if ("attribute_list".equals(name)) {
                    AttributeListParser attributeListParser = new AttributeListParser(mAttributeParser);
                    attributeListParser.addSkipAttributeName(AttributeParser.FUNCTION_NAME);
                    sub = attributeListParser.parse(functionName, subAttributeType, xmlPullParser);
                } else if ("attribute_map".equals(name)) {
                    AttributeMapParser attributeMapParser = new AttributeMapParser(mAttributeParser);
                    attributeMapParser.addSkipAttributeName(AttributeParser.FUNCTION_NAME);
                    sub = attributeMapParser.parse(functionName, subAttributeType, xmlPullParser);
                }

                if (sub != null) {
                    functionInfoList.add(Pair.create(functionName, sub));
                } else {
                    parseSuccess = false;
                }
            }
            eventType = xmlPullParser.next();
        }
        if (parseSuccess) {
            for (Pair<String, Object> functionInfo : functionInfoList) {
                parseSuccess = callFunction(attribute, functionInfo.first, functionInfo.second);
                if (!parseSuccess) {
                    break;
                }
            }

            if (parseSuccess) {
                return Pair.create(subKey, attribute);
            }
        }
        Log.w(LOG_TAG, "parse fail with key:" + key);
        return null;
    }
    private void skipInvalidAttributeXml(String key, String type, XmlPullParser xmlPullParser)
            throws IOException, XmlPullParserException {
        Stack<Integer> stack = new Stack<>();
        int eventType = xmlPullParser.getEventType();
        while (!stack.isEmpty() || (eventType != XmlPullParser.END_TAG)) {
            if (eventType == XmlPullParser.START_TAG) {
                stack.push(eventType);
            } else if (eventType == XmlPullParser.END_TAG) {
                if (stack.peek() == XmlPullParser.START_TAG) {
                    stack.pop();
                } else {
                    throw new XmlPullParserException("expected START_TAG read xml with key:" + key + ", type:" + type);
                }
            }
            eventType = xmlPullParser.next();
        }
//        while (eventType != XmlPullParser.END_TAG) {
//            if (eventType == XmlPullParser.START_TAG) {
//                do {
//                    eventType = xmlPullParser.next();
//                } while ((eventType != XmlPullParser.START_TAG) && (eventType != XmlPullParser.END_TAG));
//
//                if (eventType == XmlPullParser.START_TAG) {
//                    skipInvalidAttributeXml(key, type, xmlPullParser);
//                }
//            }
//            eventType = xmlPullParser.next();
//        }
    }

    private boolean callFunction(AttributeParser.Attribute attribute, String functionName, Object functionValue) {
        Method[] methods = attribute.getClass().getDeclaredMethods();
        Log.w(LOG_TAG, "attribute name:" + attribute.getClass() + ",size:" + methods.length);
        List<Method> matchedMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.getParameterCount() != 1) {
                continue;
            }
            matchedMethods.add(method);
        }

        for (Method method : matchedMethods) {
            String methodName = method.getName();
            Log.w(LOG_TAG, "methodName:" + methodName + ",functionName:" + functionName);
            if (!methodName.toLowerCase().equals(functionName.toLowerCase())) {
                continue;
            }

            Log.w(LOG_TAG, "functionValue:" + functionValue);
            method.setAccessible(true);
            try {
                method.invoke(attribute, functionValue);
                return true;
            } catch (IllegalAccessException | InvocationTargetException e) {
                Log.e(LOG_TAG, "not to call " + method.getName() + " of "
                        + attribute.getClass() + " with " + functionValue, e);
            }
        }
        return false;
    }

}
