package com.deepindex.attributeset.attributepath;

import android.util.ArrayMap;

import com.deepindex.attributeset.AttributeParser.Attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonAttribute extends Attribute {
    private String mName;
    private int mAge;
    private int mHigh;
    private int mWeight;
    private boolean mGender;
    private PersonAttribute mBestFriend;
    private List<PersonAttribute> mColleagues;
    private Map<String, PersonAttribute> mFriendMap;

    public PersonAttribute() {
    }

    public PersonAttribute(String name, int age, int high, int weight, boolean gender) {
        mName = name;
        mAge = age;
        mHigh = high;
        mWeight = weight;
        mGender = gender;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public int getHigh() {
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

    public void setBestFriend(PersonAttribute personAttribute) {
        if (personAttribute == null) {
            return;
        }
        mBestFriend = personAttribute;
    }

    public PersonAttribute getBestFriend() {
        return mBestFriend;
    }

    public void setColleagues(List<PersonAttribute> colleagues) {
        if (colleagues == null) {
            return;
        }
        mColleagues = new ArrayList<>(colleagues);
    }

    public List<PersonAttribute> getColleagues() {
        return mColleagues;
    }

    public void setFriends(Map<String, PersonAttribute> friendMap) {
        if (friendMap == null) {
            return;
        }
        if (mFriendMap == null) {
            mFriendMap = new ArrayMap<>();
        }
        mFriendMap.clear();
        mFriendMap.putAll(friendMap);
    }

    public Map<String, PersonAttribute> getFriends() {
        return mFriendMap;
    }
}
