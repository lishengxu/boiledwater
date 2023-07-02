package com.deepindex.attributeset.objectparser;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.deepindex.attributeset.AttributeParseHelper;
import com.deepindex.attributeset.AttributeParser;
import com.deepindex.attributeset.listparser.AttributeListParser;
import com.deepindex.attributeset.mapparser.AttributeMapParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AttributeObjectParser extends ObjectParser<AttributeParser.Attribute> {
    private static final int ATTRIBUTE_LEAST_COUNT = 3;
    private final AttributeParseHelper mAttributeParseHelper;

    public AttributeObjectParser(AttributeParser attributeParser) {
        super(attributeParser);
        mAttributeParseHelper = new AttributeParseHelper(attributeParser, this);
    }

    @Override
    public boolean checkAttributeCount(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        int attributeCount = xmlPullParser.getAttributeCount();
        if (attributeCount < ATTRIBUTE_LEAST_COUNT) {
            Log.w(LOG_TAG, "attribute count is " + attributeCount + " less than "
                + ATTRIBUTE_LEAST_COUNT + " with with key:" + key + ", type:" + type);
            return false;
        }
        return true;
    }

    @Override
    public AttributeParser.Attribute parse(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        if (!checkAttributeCount(key, type, xmlPullParser)) {
            return null;
        }

        return parseObject(key, type, xmlPullParser);
    }

    @Override
    protected AttributeParser.Attribute parseObject(String key, String type, XmlPullParser xmlPullParser)
            throws XmlPullParserException, IOException {
        AttributeParser.Attribute attribute = mAttributeParseHelper.parseAttribute(key, type, xmlPullParser);

        int eventType;
        do {
            eventType = xmlPullParser.next();
        } while ((eventType != XmlPullParser.START_TAG) && (eventType != XmlPullParser.END_TAG));

        if (eventType == XmlPullParser.END_TAG) {
            if (attribute == null) {
                Log.w(LOG_TAG, "attribute is invalid with key:" + key + ", type:" + type);
            }
            return attribute;
        }

        if (attribute == null) {
            //skip attribute stack
            Log.w(LOG_TAG, "attribute is invalid with key:" + key + ", type:" + type);
            skipInvalidAttributeXml(key, type, xmlPullParser);
            return null;
        }

        /**
         *
         * "<attribute key=\"key1\" type=\"PersonAttribute\" name=\"li\" age=\"20\" high=\"180\" weight=\"150\" >\n"
         * "</attribute>\n"
         * "<attribute key=\"key2\" type=\"PersonAttribute\" name=\"li\" age=\"20\" high=\"180\" weight=\"150\" >\n"
         * "    <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wang\" age=\"24\" high=\"175\" weight=\"160\" />\n"
         * "</attribute>\n"
         */
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
                    ObjectAttributeParser objectAttributeParser = new ObjectAttributeParser(mAttributeParser);
                    objectAttributeParser.addSkipAttributeName(AttributeParser.FUNCTION_NAME);
                    sub = objectAttributeParser.parse(functionName, subAttributeType, xmlPullParser);
//                    AttributeObjectParser attributeObjectParser = new AttributeObjectParser(mAttributeParser);
//                    attributeObjectParser.addSkipAttributeName(AttributeParser.FUNCTION_NAME);
//                    sub = attributeObjectParser.parse(functionName, subAttributeType, xmlPullParser);
                } else if ("attribute_list".equals(name)) {
//                    ListAttributeParser listAttributeParser = new ListAttributeParser(mAttributeParser);
//                    listAttributeParser.parse(functionName, subAttributeType, xmlPullParser);
                    AttributeListParser attributeListParser = new AttributeListParser(mAttributeParser);
                    attributeListParser.addSkipAttributeName(AttributeParser.FUNCTION_NAME);
                    sub = attributeListParser.parse(functionName, subAttributeType, xmlPullParser);
                } else if ("attribute_map".equals(name)) {
                    AttributeMapParser attributeMapParser = new AttributeMapParser(mAttributeParser);
                    attributeMapParser.addSkipAttributeName(AttributeParser.FUNCTION_NAME);
                    sub = attributeMapParser.parse(functionName, subAttributeType, xmlPullParser);
                } else {
                    //xml skip
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
                return attribute;
            }
        }
        Log.w(LOG_TAG, "parse fail with key:" + key);
        return null;
    }

    /**
     *

     + "<attribute key=\"key25\" type=\"PersonAttribute\" name=\"li\" age=\"20\" high=\"180\" weight=\"150\" >\n"
     + "    <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
     + "        <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" >\n"
     + "            <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
     + "                <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" >\n"
     + "                </sub>"
     + "                <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" />\n"
     + "                <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" >\n"
     + "                </sub>"
     + "             </attribute_map>\n"
     + "        </sub>"
     + "        <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" >\n"
     + "            <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
     + "                <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" >\n"
     + "                </sub>"
     + "                <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" />\n"
     + "                <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" >\n"
     + "                </sub>"
     + "             </attribute_map>\n"
     + "        </sub>"
     + "        <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" >\n"
     + "        </sub>"
     + "    </attribute_map>\n"
     * "<attribute key=\"key1\" type=\"PersonAttribute\" name=\"li\" age=\"20\" high=\"180\" weight=\"150\" >\n"
     * "</attribute>\n"
     * "<attribute key=\"key2\" type=\"PersonAttribute\" name=\"li\" age=\"20\" high=\"180\" weight=\"150\" >\n"
     * "    <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wang\" age=\"24\" high=\"175\" weight=\"160\" >\n"
     * "        <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wang\" age=\"24\" high=\"175\" weight=\"160\" >\n"
     * "            <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wang\" age=\"24\" high=\"175\" weight=\"160\" />\n"
     * "            <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wang\" age=\"24\" high=\"175\" weight=\"160\" />\n"
     * "        </attribute>
     * "    </attribute>
     * "    <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wang\" age=\"24\" high=\"175\" weight=\"160\" />\n"
     * "    <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wang\" age=\"24\" high=\"175\" weight=\"160\" />\n"
     * "    <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wang\" age=\"24\" high=\"175\" weight=\"160\" />\n"
     * "</attribute>\n"
     + "</attribute>"
     */
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
