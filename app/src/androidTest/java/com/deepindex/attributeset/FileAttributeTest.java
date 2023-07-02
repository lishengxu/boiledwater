package com.deepindex.attributeset;

import android.content.Context;

import com.deepindex.attributeset.attributepath.PersonAttribute;
import java.io.IOException;
import java.io.InputStream;

import androidx.test.platform.app.InstrumentationRegistry;
import org.xmlpull.v1.XmlPullParserException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class FileAttributeTest {
    private AttributeCollection testAttribute(String fileName) {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        AttributeCollection attributeCollection = new AttributeCollection();
        AttributeParser parser = new AttributeParser(context, attributeCollection);
        assertEquals(attributeCollection, parser.getAttributeCollection());

        try (InputStream inputStream = context.getAssets().open(fileName)) {
            assertTrue(parser.parse(inputStream));
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return attributeCollection;
    }

    //Boolean, Byte, Short, Int, Long, Float, Double, Char, String, custom
    public void parseFile() {
        String fileName = "attributetest.xml";
        AttributeCollection attributeCollection = testAttribute(fileName);
        assertEquals("value1", attributeCollection.getString("key1"));
        assertEquals("value1", attributeCollection.getString("key2"));
        assertEquals(1234, attributeCollection.getInt("key3"));
        PersonAttribute result = attributeCollection.getAttribute("key4");
        assertEquals("li", result.getName());
        assertEquals(20, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        result = attributeCollection.getAttribute("key44");
        assertEquals("wang", result.getName());
        assertEquals(24, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
    }

    public void parse() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        AttributeCollection attributeCollection = new AttributeCollection();
        AttributeParser parser = new AttributeParser(context, attributeCollection);
        assertEquals(attributeCollection, parser.getAttributeCollection());

        String fileName = "attributetest.xml";
        try (InputStream inputStream = context.getAssets().open(fileName)) {
            assertTrue(parser.parse(inputStream));
            InputStream inputStream2 = context.getAssets().open("hello");
            assertTrue(parser.parse(inputStream2));
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }

}