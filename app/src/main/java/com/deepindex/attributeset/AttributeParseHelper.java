package com.deepindex.attributeset;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.Nullable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AttributeParseHelper {
    private static final String LOG_TAG = AttributeParser.LOG_TAG;
    private final AttributeParser mAttributeParser;
    private final BaseParser<?> mCallParser;

    public AttributeParseHelper(AttributeParser attributeParser, BaseParser<?> callParser) {
        mAttributeParser = attributeParser;
        mCallParser = callParser;
    }

    @Nullable
    public AttributeParser.Attribute parseAttribute(String key, String type, XmlPullParser xmlPullParser)
            throws IOException, XmlPullParserException {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(type) || (xmlPullParser == null)) {
            return null;
        }
        String classPath = mAttributeParser.getAttributeClassPath() + "." + type;
        AttributeParser.Attribute attribute = newInstance(classPath, AttributeParser.Attribute.class);
        if (attribute == null) {
            return null;
        }
        List<Pair<String, String>> parsedAttributes = new ArrayList<>();
        for (int i = 0; i < xmlPullParser.getAttributeCount(); ++i) {
            String attributeName = xmlPullParser.getAttributeName(i);
            if (mCallParser.skipAttributeName(attributeName)) {
                continue;
            }
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (TextUtils.isEmpty(attributeValue)) {
                Log.w(LOG_TAG, attributeName + " is empty with key:" + key);
                continue;
            }
            parsedAttributes.add(Pair.create(attributeName, attributeValue));
        }
        Method[] methods = attribute.getClass().getDeclaredMethods();
        Log.w(LOG_TAG, "attribute name:" + attribute.getClass() + ",size:" + methods.length);
        List<Method> matchedMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.getParameterCount() != 1) {
                continue;
            }
            if (!method.getName().startsWith(mAttributeParser.getPreMethodName())) {
                continue;
            }
            matchedMethods.add(method);
        }

        List<ParseResultInfo> parseInfoList = new ArrayList<>();
        for (Pair<String, String> parsedAttribute : parsedAttributes) {
            String attributeName = parsedAttribute.first;
            String attributeValue = parsedAttribute.second;
            for (Method method : matchedMethods) {
                String methodName = method.getName();
                Log.w(LOG_TAG, "methodName:" + methodName + ",attributeName:" + attributeName);
                if (!isMatchMethodName(methodName, attributeName, mAttributeParser.getPreMethodName())) {
                    continue;
                }

                Class<?> classType = method.getParameterTypes()[0];
                Object object = constructObject(attributeValue, classType);
                Log.w(LOG_TAG, "attributeValue:"
                        + attributeValue
                        + ",classType:"
                        + classType.getName()
                        + ",object:"
                        + object);
                if (object == null) {
                    Log.w(LOG_TAG, attributeValue + " can not cast to " + classType.getName());
                    continue;
                }
                method.setAccessible(true);
                try {
                    method.invoke(attribute, object);
                    parseInfoList.add(
                            new ParseResultInfo(attributeName, methodName, attributeValue, classType,
                                    object));
                    break;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    Log.e(LOG_TAG, "not to call " + method.getName() + " of "
                            + attribute.getClass() + " with " + object, e);
                }
            }
        }

        for (Pair<String, String> parsedAttribute : parsedAttributes) {
            boolean isParsed = false;
            String attributeName = parsedAttribute.first;
            for (ParseResultInfo parseInfo : parseInfoList) {
                if (attributeName.equals(parseInfo.mAttributeName)) {
                    isParsed = true;
                    break;
                }
            }
            if (!isParsed) {
                Log.d(LOG_TAG, attributeName + "=" + parsedAttribute.second
                        + " is not parsed with key:" + key);
            }
        }

        for (Method method : matchedMethods) {
            boolean isParsed = false;
            String methodName = method.getName();
            for (ParseResultInfo parseInfo : parseInfoList) {
                if (methodName.equals(parseInfo.mMethodName)) {
                    isParsed = true;
                    break;
                }
            }
            if (!isParsed) {
                Log.d(LOG_TAG, method.toString() + " is not matched with key:" + key);
            }
        }

        return parseInfoList.isEmpty() ? null : attribute;
    }

    private static boolean isMatchMethodName(String methodName, String attributeName,
                                             String preMethodName) {
        return methodName.toLowerCase().equals(preMethodName + attributeName.toLowerCase());
    }

    @SuppressWarnings("unchecked")
    private static <T> T newInstance(String classPath, Class<?> typeOfT) {
        if (TextUtils.isEmpty(classPath)) {
            return null;
        }
        if (typeOfT == null) {
            Log.w(LOG_TAG, "class type is null with " + classPath);
            return null;
        }
        Class<?> className = null;
        try {
            className = Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            Log.e(LOG_TAG, classPath + " is not find", e);
        }
        if (className == null) {
            return null;
        }

        if (!typeOfT.equals(className) && !typeOfT.isAssignableFrom(className)) {
            Log.w(LOG_TAG,
                    "expected " + typeOfT.getName() + " but was actually " + className.getName());
            Log.w(LOG_TAG, "Attempt to cast generated internal exception:",
                    new ClassCastException(
                            className.getName() + " cannot be cast to " + typeOfT.getName()));
            return null;
        }

        try {
            return (T) className.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            Log.e(LOG_TAG, "not to instance " + className.getName(), e);
        }
        return null;
    }

    private static Object constructObject(String value, Class<?> classType) {
        try {
            if (classType == boolean.class) {
                return Boolean.valueOf(value);
            } else if (classType == byte.class) {
                return Byte.valueOf(value);
            } else if (classType == short.class) {
                return Short.valueOf(value);
            } else if (classType == int.class) {
                return Integer.valueOf(value);
            } else if (classType == long.class) {
                return Long.valueOf(value);
            } else if (classType == float.class) {
                return Float.valueOf(value);
            } else if (classType == double.class) {
                return Double.valueOf(value);
            } else if (classType == char.class) {
                return value.charAt(0);
            } else if (classType == String.class) {
                return value;
            }
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG, value + " can not cast to " + classType.getName(), e);
            return null;
        }

        Constructor<?> constructor;
        try {
            constructor = classType.getDeclaredConstructor(String.class);
        } catch (NoSuchMethodException e) {
            Log.e(LOG_TAG, classType.getName()
                    + " has not constructor with a String parameter", e);
            return null;
        }
        constructor.setAccessible(true);
        try {
            return constructor.newInstance(value);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            Log.e(LOG_TAG, "not to instance " + classType.getName() + " with " + value, e);
        }
        return null;
    }
}
