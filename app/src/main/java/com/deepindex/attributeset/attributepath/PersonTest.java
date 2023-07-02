package com.deepindex.attributeset.attributepath;

import com.deepindex.attributeset.AttributeParser.Attribute;

public class PersonTest extends Attribute {
    private String mName;
    private int mAge;
    private int mHigh;
    private int mWeight;
    private boolean mGender;

    public PersonTest() {
    }

    public PersonTest(String name, int age, int high, int weight, boolean gender) {
        mName = name;
        mAge = age;
        mHigh = high;
        mWeight = weight;
        mGender = gender;
    }

    public String getName2() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getAge2() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public int getHigh2() {
        return mHigh;
    }

    public void setHigh(int high) {
        mHigh = high;
    }

    public int getWeight() {
        return mWeight;
    }

    public void setWeight(int weight) {
        mWeight = weight;
    }

    public boolean getGender() {
        return mGender;
    }

    public void setGender(boolean gender) {
        mGender = gender;
    }
}
