package com.deepindex.attributeset;

import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Map;

/**
 * A Attribute Collection from keys to values of various types.
 *
 * @param <T> the type of keys maintained by this base collection.
 *
 * @author  834150598@qq.com
 */
public class BaseCollection<T> {
    protected String mLogTag = "BaseCollection";

    protected final Map<T, Object> mMap;

    public BaseCollection() {
        mMap = new ArrayMap<>();
    }

    /**
     * Inserts a Boolean value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value a boolean value to store for the given key.
     */
    public void put(@NonNull T key, boolean value) {
        mMap.put(key, value);
    }

    /**
     * Inserts a byte value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value a byte value to store for the given key.
     */
    public void put(@NonNull T key, byte value) {
        mMap.put(key, value);
    }

    /**
     * Inserts a short value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value a short value to store for the given key.
     */
    public void put(@NonNull T key, short value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an int value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an int value to store for the given key.
     */
    public void put(@NonNull T key, int value) {
        mMap.put(key, value);
    }

    /**
     * Inserts a long value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value a long value to store for the given key.
     */
    public void put(@NonNull T key, long value) {
        mMap.put(key, value);
    }

    /**
     * Inserts a float value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value a float value to store for the given key.
     */
    public void put(@NonNull T key, float value) {
        mMap.put(key, value);
    }

    /**
     * Inserts a double value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value a double value to store for the given key.
     */
    public void put(@NonNull T key, double value) {
        mMap.put(key, value);
    }

    /**
     * Inserts a char value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value a char value to store for the given key.
     */
    public void put(@NonNull T key, char value) {
        mMap.put(key, value);
    }

    /**
     * Inserts a String value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value a String value to store for the given key.
     */
    public void put(@NonNull T key,@NonNull String value) {
        mMap.put(key, value);
    }

    /**
     * Inserts a Attribute value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value a {@link AttributeParser.Attribute} value to store for the given key.
     */
    public void put(@NonNull T key, @NonNull AttributeParser.Attribute value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayList<Boolean> value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayList<Boolean> value to store for the given key.
     */
    public void putBooleanArrayList(@NonNull T key, @NonNull ArrayList<Boolean> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayList<Byte> value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayList<Byte> value to store for the given key.
     */
    public void putByteArrayList(@NonNull T key, @NonNull ArrayList<Byte> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayList<Short> value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayList<Short> value to store for the given key.
     */
    public void putShortArrayList(@NonNull T key, @NonNull ArrayList<Short> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayList<Integer> value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayList<Integer> value to store for the given key.
     */
    public void putIntegerArrayList(@NonNull T key, @NonNull ArrayList<Integer> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayList<Long> value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayList<Long> value to store for the given key.
     */
    public void putLongArrayList(@NonNull T key, @NonNull ArrayList<Long> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayList<Float> value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayList<Float> value to store for the given key.
     */
    public void putFloatArrayList(@NonNull T key, @NonNull ArrayList<Float> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayList<Double> value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayList<Double> value to store for the given key.
     */
    public void putDoubleArrayList(@NonNull T key, @NonNull ArrayList<Double> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayList<Character> value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayList<Character> value to store for the given key.
     */
    public void putCharacterArrayList(@NonNull T key, @NonNull ArrayList<Character> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayList<String> value into the mapping of this AttributeCollection, replacing
     * any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayList<String> value to store for the given key.
     */
    public void putStringArrayList(@NonNull T key, @NonNull ArrayList<String> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayList<AttributeParser.Attribute> value into the mapping of this AttributeCollection,
     * replacing any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayList<{@link AttributeParser.Attribute}> value to store for the given key.
     */
    public <R extends AttributeParser.Attribute> void putAttributeArrayList(@NonNull T key,
                                                                         @NonNull ArrayList<R> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayMap<String, Boolean> value into the mapping of this AttributeCollection,
     * replacing any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayMap<String, Boolean> value to store for the given key.
     */
    public void putBooleanArrayMap(@NonNull T key, @NonNull ArrayMap<String, Boolean> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayMap<String, Byte> value into the mapping of this AttributeCollection,
     * replacing any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayMap<String, Byte> value to store for the given key.
     */
    public void putByteArrayMap(@NonNull T key, @NonNull ArrayMap<String, Byte> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayMap<String, Short> value into the mapping of this AttributeCollection,
     * replacing any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayMap<String, Short> value to store for the given key.
     */
    public void putShortArrayMap(@NonNull T key, @NonNull ArrayMap<String, Short> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayMap<String, Integer> value into the mapping of this AttributeCollection,
     * replacing any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayMap<String, Integer> value to store for the given key.
     */
    public void putIntegerArrayMap(@NonNull T key, @NonNull ArrayMap<String, Integer> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayMap<String, Long> value into the mapping of this AttributeCollection,
     * replacing any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayMap<String, Long> value to store for the given key.
     */
    public void putLongArrayMap(@NonNull T key, @NonNull ArrayMap<String, Long> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayMap<String, Float> value into the mapping of this AttributeCollection,
     * replacing any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayMap<String, Float> value to store for the given key.
     */
    public void putFloatArrayMap(@NonNull T key, @NonNull ArrayMap<String, Float> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayMap<String, Double> value into the mapping of this AttributeCollection,
     * replacing any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayMap<String, Double> value to store for the given key.
     */
    public void putDoubleArrayMap(@NonNull T key, @NonNull ArrayMap<String, Double> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayMap<String, Character> value into the mapping of this AttributeCollection,
     * replacing any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayMap<String, Character> value to store for the given key.
     */
    public void putCharacterArrayMap(@NonNull T key, @NonNull ArrayMap<String, Character> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayMap<String, String> value into the mapping of this AttributeCollection,
     * replacing any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayMap<String, String> value to store for the given key.
     */
    public void putStringArrayMap(@NonNull T key, @NonNull ArrayMap<String, String> value) {
        mMap.put(key, value);
    }

    /**
     * Inserts an ArrayMap<String, AttributeParser.Attribute> value into the mapping of this
     * AttributeCollection, replacing any existing value for the given key.
     *
     * @param key The key under which to store the value.
     * @param value an ArrayMap<String, {@link AttributeParser.Attribute}> value
     *             to store for the given key.
     */
    public <R extends AttributeParser.Attribute> void putAttributeArrayMap(@NonNull T key,
                                                                        @NonNull ArrayMap<String, R> value) {
        mMap.put(key, value);
    }

    public Object put(@NonNull T key, @NonNull Object value) {
        return mMap.put(key, value);
    }

    /**
     * Returns the value associated with the given key, or false if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return a boolean value to store for the given key, or false if no mapping.
     */
    public boolean getBoolean(@NonNull T key) {
        return getBoolean(key, false);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return a boolean value to store for the given key.
     */
    public boolean getBoolean(@NonNull T key, boolean defaultValue) {
        return getValue(key, defaultValue, Boolean.class);
    }

    /**
     * Returns the value associated with the given key, or (byte) 0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return a byte value to store for the given key, or (byte) 0 if no mapping.
     */
    public byte getByte(@NonNull T key) {
        return getByte(key, (byte) 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return a byte value to store for the given key.
     */
    public byte getByte(@NonNull T key, byte defaultValue) {
        return getValue(key, defaultValue, Byte.class);
    }

    /**
     * Returns the value associated with the given key, or (short) 0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return a short value to store for the given key, or (short) 0 if no mapping.
     */
    public short getShort(@NonNull T key) {
        return getShort(key, (short) 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return a short value to store for the given key.
     */
    public short getShort(@NonNull T key, short defaultValue) {
        return getValue(key, defaultValue, Short.class);
    }

    /**
     * Returns the value associated with the given key, or 0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an int value to store for the given key, or 0 if no mapping.
     */
    public int getInt(@NonNull T key) {
        return getInt(key, 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an int value to store for the given key.
     */
    public int getInt(@NonNull T key, int defaultValue) {
        return getValue(key, defaultValue, Integer.class);
    }

    /**
     * Returns the value associated with the given key, or 0L if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return a long value to store for the given key, or 0L if no mapping.
     */
    public long getLong(@NonNull T key) {
        return getLong(key, 0L);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return a long value to store for the given key.
     */
    public long getLong(@NonNull T key, long defaultValue) {
        return getValue(key, defaultValue, Long.class);
    }

    /**
     * Returns the value associated with the given key, or 0.0f if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return a float value to store for the given key, or 0.0f if no mapping.
     */
    public float getFloat(@NonNull T key) {
        return getFloat(key, 0.0f);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return a float value to store for the given key.
     */
    public float getFloat(@NonNull T key, float defaultValue) {
        return getValue(key, defaultValue, Float.class);
    }

    /**
     * Returns the value associated with the given key, or 0.0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return a double value to store for the given key, or 0.0 if no mapping.
     */
    public double getDouble(@NonNull T key) {
        return getDouble(key, 0.0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return a double value to store for the given key.
     */
    public double getDouble(@NonNull T key, double defaultValue) {
        return getValue(key, defaultValue, Double.class);
    }

    /**
     * Returns the value associated with the given key, or (char) 0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return a char value to store for the given key, or (char) 0 if no mapping.
     */
    public char getChar(@NonNull T key) {
        return getChar(key, (char) 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return a char value to store for the given key.
     */
    public char getChar(@NonNull T key, char defaultValue) {
        return getValue(key, defaultValue, Character.class);
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return a String value to store for the given key, or null if no mapping.
     */
    public String getString(@NonNull T key) {
        return getString(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return a String value to store for the given key.
     */
    public String getString(@NonNull T key, String defaultValue) {
        return getValue(key, defaultValue, String.class);
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return a {@link AttributeParser.Attribute} value, or null if no mapping.
     */
    public <R extends AttributeParser.Attribute> R getAttribute(@NonNull T key) {
        return getAttribute(key, null);
    }

    public <R extends AttributeParser.Attribute> R getAttribute(@NonNull T key, R defaultValue) {
        return getValue(key, defaultValue, AttributeParser.Attribute.class);
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayList<Boolean> value to store for the given key, or null if no mapping.
     */
    public ArrayList<Boolean> getBooleanArrayList(@NonNull T key) {
        return getBooleanArrayList(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayList<Boolean> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Boolean> getBooleanArrayList(@NonNull T key, ArrayList<Boolean> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayList<Boolean>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayList<Boolean>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayList<Byte> value to store for the given key, or null if no mapping.
     */
    public ArrayList<Byte> getByteArrayList(@NonNull T key) {
        return getByteArrayList(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayList<Byte> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Byte> getByteArrayList(@NonNull T key, ArrayList<Byte> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayList<Byte>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayList<Byte>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayList<Short> value to store for the given key, or null if no mapping.
     */
    public ArrayList<Short> getShortArrayList(@NonNull T key) {
        return getShortArrayList(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayList<Short> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Short> getShortArrayList(@NonNull T key, ArrayList<Short> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayList<Short>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayList<Short>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayList<Integer> value to store for the given key, or null if no mapping.
     */
    public ArrayList<Integer> getIntegerArrayList(@NonNull T key) {
        return getIntegerArrayList(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayList<Integer> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Integer> getIntegerArrayList(@NonNull T key, ArrayList<Integer> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayList<Integer>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayList<Integer>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayList<Long> value to store for the given key, or null if no mapping.
     */
    public ArrayList<Long> getLongArrayList(@NonNull T key) {
        return getLongArrayList(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayList<Long> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Long> getLongArrayList(@NonNull T key, ArrayList<Long> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayList<Long>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayList<Long>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayList<Float> value to store for the given key, or null if no mapping.
     */
    public ArrayList<Float> getFloatArrayList(@NonNull T key) {
        return getFloatArrayList(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayList<Float> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Float> getFloatArrayList(@NonNull T key, ArrayList<Float> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayList<Float>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayList<Float>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayList<Double> value to store for the given key, or null if no mapping.
     */
    public ArrayList<Double> getDoubleArrayList(@NonNull T key) {
        return getDoubleArrayList(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayList<Double> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Double> getDoubleArrayList(@NonNull T key, ArrayList<Double> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayList<Double>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayList<Double>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayList<Double> value to store for the given key, or null if no mapping.
     */
    public ArrayList<Character> getCharacterArrayList(@NonNull T key) {
        return getCharacterArrayList(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayList<Character> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Character> getCharacterArrayList(@NonNull T key,
                                                      ArrayList<Character> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayList<Character>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayList<Character>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayList<String> value to store for the given key, or null if no mapping.
     */
    public ArrayList<String> getStringArrayList(@NonNull T key) {
        return getStringArrayList(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayList<String> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<String> getStringArrayList(@NonNull T key, ArrayList<String> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayList<String>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayList<String>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayList<{@link AttributeParser.Attribute}> value to store for the given key,
     * or null if no mapping.
     */
    public <R extends AttributeParser.Attribute> ArrayList<R> getAttributeArrayList(@NonNull T key) {
        return getAttributeArrayList(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayList<{@link AttributeParser.Attribute}> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public <R extends AttributeParser.Attribute> ArrayList<R> getAttributeArrayList(
            @NonNull T key, ArrayList<R> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayList<R>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayList<AttributeParser.Attribute>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayMap<String, Boolean> value to store for the given key, or null if no mapping.
     */
    public ArrayMap<String, Boolean> getBooleanArrayMap(@NonNull T key) {
        return getBooleanArrayMap(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayMap<String, Boolean> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayMap<String, Boolean> getBooleanArrayMap(@NonNull T key,
                                                        ArrayMap<String, Boolean> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayMap<String, Boolean>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayMap<String, Boolean>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayMap<String, Byte> value to store for the given key, or null if no mapping.
     */
    public ArrayMap<String, Byte> getByteArrayMap(@NonNull T key) {
        return getByteArrayMap(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayMap<String, Byte> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayMap<String, Byte> getByteArrayMap(@NonNull T key,
                                                  ArrayMap<String, Byte> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayMap<String, Byte>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayMap<String, Byte>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayMap<String, Short> value to store for the given key, or null if no mapping.
     */
    public ArrayMap<String, Short> getShortArrayMap(@NonNull T key) {
        return getShortArrayMap(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayMap<String, Short> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayMap<String, Short> getShortArrayMap(@NonNull T key,
                                                    ArrayMap<String, Short> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayMap<String, Short>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayMap<String, Short>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayMap<String, Integer> value to store for the given key, or null if no mapping.
     */
    public ArrayMap<String, Integer> getIntegerArrayMap(@NonNull T key) {
        return getIntegerArrayMap(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayMap<String, Integer> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayMap<String, Integer> getIntegerArrayMap(@NonNull T key,
                                                        ArrayMap<String, Integer> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayMap<String, Integer>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayMap<String, Integer>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayMap<String, Long> value to store for the given key, or null if no mapping.
     */
    public ArrayMap<String, Long> getLongArrayMap(@NonNull T key) {
        return getLongArrayMap(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayMap<String, Long> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayMap<String, Long> getLongArrayMap(@NonNull T key,
                                                  ArrayMap<String, Long> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayMap<String, Long>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayMap<String, Long>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayMap<String, Float> value to store for the given key, or null if no mapping.
     */
    public ArrayMap<String, Float> getFloatArrayMap(@NonNull T key) {
        return getFloatArrayMap(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayMap<String, Float> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayMap<String, Float> getFloatArrayMap(@NonNull T key,
                                                    ArrayMap<String, Float> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayMap<String, Float>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayMap<String, Float>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayMap<String, Double> value to store for the given key, or null if no mapping.
     */
    public ArrayMap<String, Double> getDoubleArrayMap(@NonNull T key) {
        return getDoubleArrayMap(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayMap<String, Double> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayMap<String, Double> getDoubleArrayMap(@NonNull T key,
                                                      ArrayMap<String, Double> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayMap<String, Double>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayMap<String, Double>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayMap<String, Double> value to store for the given key, or null if no mapping.
     */
    public ArrayMap<String, Character> getCharacterArrayMap(@NonNull T key) {
        return getCharacterArrayMap(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayMap<String, Character> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayMap<String, Character> getCharacterArrayMap(@NonNull T key,
                                                        ArrayMap<String, Character> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayMap<String, Character>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayMap<String, Character>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayMap<String, String> value to store for the given key, or null if no mapping.
     */
    public ArrayMap<String, String> getStringArrayMap(@NonNull T key) {
        return getStringArrayMap(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayMap<String, String> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public ArrayMap<String, String> getStringArrayMap(@NonNull T key,
                                                      ArrayMap<String, String> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayMap<String, String>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayMap<String, String>", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @return an ArrayMap<String, {@link AttributeParser.Attribute}> value to store for the given key,
     * or null if no mapping.
     */
    public <R extends AttributeParser.Attribute> ArrayMap<String, R> getAttributeArrayMap(@NonNull T key) {
        return getAttributeArrayMap(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @return an ArrayMap<String, {@link AttributeParser.Attribute}> value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    public <R extends AttributeParser.Attribute> ArrayMap<String, R> getAttributeArrayMap(
            @NonNull T key, ArrayMap<String, R> defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return (ArrayMap<String, R>) value;
        } catch (ClassCastException e) {
            typeWarning(key, value, "ArrayMap<String, AttributeParser.Attribute>",
                    defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the typeOfT type exists for the given key.
     *
     * @param key The key under which to store the value.
     * @param defaultValue value to return if key does not exist.
     * @param typeOfT the desired class type.
     * @return a Object value to store for the given key.
     */
    @SuppressWarnings("unchecked")
    private <R> R getValue(@NonNull T key, R defaultValue, Class<?> typeOfT) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        }

        if (typeOfT == null) {
            return defaultValue;
        }

        if (!typeOfT.equals(value.getClass()) && !typeOfT.isAssignableFrom(value.getClass())) {
            String className =
                (defaultValue != null) ? defaultValue.getClass().getName() : typeOfT.getName();
            typeWarning(key, value, className, defaultValue, new ClassCastException(
                value.getClass().getName() + " cannot be cast to " + className));
            return defaultValue;
        }
        return (R) value;
    }

    /**
     * Removes all elements from the mapping of this AttributeCollection.
     */
    public void clear() {
        mMap.clear();
    }

    // Log a message if the value was non-null but not of the expected type
    protected void typeWarning(T key, Object value, String className, Object defaultValue,
        ClassCastException e) {
        StringBuilder sb = new StringBuilder();
        sb.append("Key ");
        sb.append(key);
        sb.append(" expected ");
        sb.append(className);
        sb.append(" but value was a ");
        sb.append(value.getClass().getName());
        sb.append(".  The default value ");
        sb.append(defaultValue);
        sb.append(" was returned.");
        Log.w(mLogTag, sb.toString());
        Log.w(mLogTag, "Attempt to cast generated internal exception:", e);
    }

}
