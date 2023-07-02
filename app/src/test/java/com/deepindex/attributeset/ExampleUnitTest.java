package com.deepindex.attributeset;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test() {
        float value = Float.parseFloat("123.0f");
        assertEquals(123.0f, value, 0.0000001);
        String value2 = getValue();
    }

    public <T> T getValue() {
        return (T) "hello";
    }
}