package com.deepindex.attributeset;

public class ParseResultInfo {
    public String mAttributeName;
    public String mMethodName;
    public String mAttributeValue;
    public Class<?> mClassType;
    public Object mObject;

    public ParseResultInfo(String attributeName, String methodName, String attributeValue,
        Class<?> classType, Object object) {
        mAttributeName = attributeName;
        mMethodName = methodName;
        mAttributeValue = attributeValue;
        mClassType = classType;
        mObject = object;
    }
}
