package com.deepindex.attributeset;


import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import com.deepindex.attributeset.attributepath.PersonAttribute;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.xmlpull.v1.XmlPullParserException;

import static junit.framework.TestCase.*;

@RunWith(AndroidJUnit4.class)
public class ObjectAttributeTest {
    private static final String XML_START = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
    private static final String ATTRIBUTE_BEGIN = "<attribute_info>\n";
    private static final String ATTRIBUTE_END = "</attribute_info>";

    private AttributeCollection parseAttribute(String fileInfo) {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        AttributeCollection attributeCollection = new AttributeCollection();
        AttributeParser parser = new AttributeParser(context, attributeCollection);
        assertEquals(attributeCollection, parser.getAttributeCollection());

        try (InputStream inputStream = new ByteArrayInputStream(
            fileInfo.getBytes(StandardCharsets.UTF_8))) {
            assertTrue(parser.parse(inputStream));
        } catch (IOException | XmlPullParserException e) {
            fail(e.getMessage());
        }
        return attributeCollection;
    }

    @Test
    public void baseBooleanObjectAttribute() {
        String fileInfo = XML_START
            + ATTRIBUTE_BEGIN
            + "<attribute key=\"key0\" type=\"boolean\" value=\"true\"/>\n"
            + "<attribute key=\"key1\" type=\"boolean\" value=\"true\">\n"
            + "</attribute>"
            + "<attribute key1=\"key2\" type=\"boolean\" value=\"true\"/>\n"
            + "<attribute key=\"key3\" typ=\"boolean\" value=\"true\"/>\n"
            + "<attribute key=\"key4\" type=\"boolean\" valu=\"true\"/>\n"
            + "<attribute key=\"key5\" type=\"boolea\" value=\"true\"/>\n"
            + "<attribute key=\"key6\" type=\"boolean\" value=\"true\" append=\"append1\"/>\n"
            + "<attribute key=\"key7\" type=\"boolean\" value=\"abcd\"/>\n"
            + "<attribute key=\"key8\" type=\"boolean\" value=\"true\"/>\n"
            + "<attribute key=\"key9\" type=\"Boolean\" value=\"true\"/>\n"
            + "<attribute key=\"key8\" type=\"boolean\" value=\"false\"/>\n"
            + ATTRIBUTE_END;
        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        assertTrue(attributeCollection.getBoolean("key0"));
        assertTrue(attributeCollection.getBoolean("key1"));
        assertFalse(attributeCollection.getBoolean("key2"));
        assertFalse(attributeCollection.getBoolean("key3"));
        assertFalse(attributeCollection.getBoolean("key4"));
        assertFalse(attributeCollection.getBoolean("key5"));
        assertFalse(attributeCollection.getBoolean("key6"));
        assertFalse(attributeCollection.getBoolean("key7"));
        assertFalse(attributeCollection.getBoolean("key8"));
        assertTrue(attributeCollection.getBoolean("key9"));
        assertTrue(attributeCollection.getBoolean("key10", true));
    }

    @Test
    public void baseByteObjectAttribute() {
        String fileInfo = XML_START
            + ATTRIBUTE_BEGIN
            + "<attribute key=\"key0\" type=\"byte\" value=\"123\"/>\n"
            + "<attribute key=\"key1\" type=\"byte\" value=\"123\">\n"
            + "</attribute>"
            + "<attribute key1=\"key2\" type=\"byte\" value=\"123\"/>\n"
            + "<attribute key=\"key3\" typ=\"byte\" value=\"123\"/>\n"
            + "<attribute key=\"key4\" type=\"byte\" valu=\"123\"/>\n"
            + "<attribute key=\"key5\" type=\"byt\" value=\"123\"/>\n"
            + "<attribute key=\"key6\" type=\"byte\" value=\"123\" append=\"append1\"/>\n"
            + "<attribute key=\"key7\" type=\"byte\" value=\"abcd\"/>\n"
            + "<attribute key=\"key8\" type=\"byte\" value=\"12\"/>\n"
            + "<attribute key=\"key9\" type=\"Byte\" value=\"123\"/>\n"
            + "<attribute key=\"key8\" type=\"byte\" value=\"123\"/>\n"
            + ATTRIBUTE_END;
        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        assertEquals(123, attributeCollection.getByte("key0"));
        assertEquals(123, attributeCollection.getByte("key1"));
        assertEquals(0, attributeCollection.getByte("key2"));
        assertEquals(0, attributeCollection.getByte("key3"));
        assertEquals(0, attributeCollection.getByte("key4"));
        assertEquals(0, attributeCollection.getByte("key5"));
        assertEquals(0, attributeCollection.getByte("key6"));
        assertEquals(0, attributeCollection.getByte("key7"));
        assertEquals(123, attributeCollection.getByte("key8"));
        assertEquals(123, attributeCollection.getByte("key9"));
        assertEquals(100, attributeCollection.getByte("key10", (byte) 100));
    }

    @Test
    public void baseShortObjectAttribute() {
        String fileInfo = XML_START
            + ATTRIBUTE_BEGIN
            + "<attribute key=\"key0\" type=\"short\" value=\"123\"/>\n"
            + "<attribute key=\"key1\" type=\"short\" value=\"123\">\n"
            + "</attribute>"
            + "<attribute key1=\"key2\" type=\"short\" value=\"123\"/>\n"
            + "<attribute key=\"key3\" typ=\"short\" value=\"123\"/>\n"
            + "<attribute key=\"key4\" type=\"short\" valu=\"123\"/>\n"
            + "<attribute key=\"key5\" type=\"shor\" value=\"123\"/>\n"
            + "<attribute key=\"key6\" type=\"short\" value=\"123\" append=\"append1\"/>\n"
            + "<attribute key=\"key7\" type=\"short\" value=\"abcd\"/>\n"
            + "<attribute key=\"key8\" type=\"short\" value=\"123\"/>\n"
            + "<attribute key=\"key9\" type=\"Short\" value=\"1234\"/>\n"
            + "<attribute key=\"key8\" type=\"short\" value=\"1234\"/>\n"
            + ATTRIBUTE_END;
        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        assertEquals(123, attributeCollection.getShort("key0"));
        assertEquals(123, attributeCollection.getShort("key1"));
        assertEquals(0, attributeCollection.getShort("key2"));
        assertEquals(0, attributeCollection.getShort("key3"));
        assertEquals(0, attributeCollection.getShort("key4"));
        assertEquals(0, attributeCollection.getShort("key5"));
        assertEquals(0, attributeCollection.getShort("key6"));
        assertEquals(0, attributeCollection.getShort("key7"));
        assertEquals(1234, attributeCollection.getShort("key8"));
        assertEquals(1234, attributeCollection.getShort("key9"));
        assertEquals(100, attributeCollection.getShort("key10", (short) 100));
    }

    @Test
    public void baseIntObjectAttribute() {
        String fileInfo = XML_START
            + ATTRIBUTE_BEGIN
            + "<attribute key=\"key0\" type=\"int\" value=\"123\"/>\n"
            + "<attribute key=\"key1\" type=\"int\" value=\"123\">\n"
            + "</attribute>"
            + "<attribute key1=\"key2\" type=\"int\" value=\"123\"/>\n"
            + "<attribute key=\"key3\" typ=\"int\" value=\"123\"/>\n"
            + "<attribute key=\"key4\" type=\"int\" valu=\"123\"/>\n"
            + "<attribute key=\"key5\" type=\"in\" value=\"123\"/>\n"
            + "<attribute key=\"key6\" type=\"int\" value=\"123\" append=\"append1\"/>\n"
            + "<attribute key=\"key7\" type=\"int\" value=\"abcd\"/>\n"
            + "<attribute key=\"key8\" type=\"int\" value=\"123\"/>\n"
            + "<attribute key=\"key9\" type=\"Int\" value=\"1234\"/>\n"
            + "<attribute key=\"key8\" type=\"Int\" value=\"1234\"/>\n"
            + ATTRIBUTE_END;
        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        assertEquals(123, attributeCollection.getInt("key0"));
        assertEquals(123, attributeCollection.getInt("key1"));
        assertEquals(0, attributeCollection.getInt("key2"));
        assertEquals(0, attributeCollection.getInt("key3"));
        assertEquals(0, attributeCollection.getInt("key4"));
        assertEquals(0, attributeCollection.getInt("key5"));
        assertEquals(0, attributeCollection.getInt("key6"));
        assertEquals(0, attributeCollection.getInt("key7"));
        assertEquals(1234, attributeCollection.getInt("key8"));
        assertEquals(1234, attributeCollection.getInt("key9"));
        assertEquals(100, attributeCollection.getInt("key10", 100));
    }

    @Test
    public void baseLongObjectAttribute() {
        String fileInfo = XML_START
            + ATTRIBUTE_BEGIN
            + "<attribute key=\"key0\" type=\"long\" value=\"123\"/>\n"
            + "<attribute key=\"key1\" type=\"long\" value=\"123\">\n"
            + "</attribute>"
            + "<attribute key1=\"key2\" type=\"long\" value=\"123\"/>\n"
            + "<attribute key=\"key3\" typ=\"long\" value=\"123\"/>\n"
            + "<attribute key=\"key4\" type=\"long\" valu=\"123\"/>\n"
            + "<attribute key=\"key5\" type=\"lon\" value=\"123\"/>\n"
            + "<attribute key=\"key6\" type=\"long\" value=\"123\" append=\"append1\"/>\n"
            + "<attribute key=\"key7\" type=\"long\" value=\"abcd\"/>\n"
            + "<attribute key=\"key8\" type=\"long\" value=\"123\"/>\n"
            + "<attribute key=\"key9\" type=\"Long\" value=\"1234\"/>\n"
            + "<attribute key=\"key8\" type=\"long\" value=\"1234\"/>\n"
            + ATTRIBUTE_END;
        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        assertEquals(123, attributeCollection.getLong("key0"));
        assertEquals(123, attributeCollection.getLong("key1"));
        assertEquals(0, attributeCollection.getLong("key2"));
        assertEquals(0, attributeCollection.getLong("key3"));
        assertEquals(0, attributeCollection.getLong("key4"));
        assertEquals(0, attributeCollection.getLong("key5"));
        assertEquals(0, attributeCollection.getLong("key6"));
        assertEquals(0, attributeCollection.getLong("key7"));
        assertEquals(1234, attributeCollection.getLong("key8"));
        assertEquals(1234, attributeCollection.getLong("key9"));
        assertEquals(100, attributeCollection.getLong("key10", 100L));
    }

    @Test
    public void baseFloatObjectAttribute() {
        String fileInfo = XML_START
            + ATTRIBUTE_BEGIN
            + "<attribute key=\"key0\" type=\"float\" value=\"123.0f\"/>\n"
            + "<attribute key=\"key1\" type=\"float\" value=\"123.0f\">\n"
            + "</attribute>"
            + "<attribute key1=\"key2\" type=\"float\" value=\"123\"/>\n"
            + "<attribute key=\"key3\" typ=\"float\" value=\"123\"/>\n"
            + "<attribute key=\"key4\" type=\"float\" valu=\"123\"/>\n"
            + "<attribute key=\"key5\" type=\"floa\" value=\"123\"/>\n"
            + "<attribute key=\"key6\" type=\"float\" value=\"123\" append=\"append1\"/>\n"
            + "<attribute key=\"key7\" type=\"float\" value=\"abcd\"/>\n"
            + "<attribute key=\"key8\" type=\"float\" value=\"123\"/>\n"
            + "<attribute key=\"key9\" type=\"Float\" value=\"1234\"/>\n"
            + "<attribute key=\"key8\" type=\"float\" value=\"1234\"/>\n"
            + ATTRIBUTE_END;
        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        assertEquals(123f, attributeCollection.getFloat("key0"), 0.0000001f);
        assertEquals(123f, attributeCollection.getFloat("key1"), 0.0000001f);
        assertEquals(0f, attributeCollection.getFloat("key2"), 0.0000001f);
        assertEquals(0f, attributeCollection.getFloat("key3"), 0.0000001f);
        assertEquals(0f, attributeCollection.getFloat("key4"), 0.0000001f);
        assertEquals(0f, attributeCollection.getFloat("key5"), 0.0000001f);
        assertEquals(0f, attributeCollection.getFloat("key6"), 0.0000001f);
        assertEquals(0f, attributeCollection.getFloat("key7"), 0.0000001f);
        assertEquals(1234f, attributeCollection.getFloat("key8"), 0.0000001f);
        assertEquals(1234f, attributeCollection.getFloat("key9"), 0.0000001f);
        assertEquals(100f, attributeCollection.getFloat("key10", 100f), 0.0000001f);
    }

    @Test
    public void baseDoubleObjectAttribute() {
        String fileInfo = XML_START
            + ATTRIBUTE_BEGIN
            + "<attribute key=\"key0\" type=\"double\" value=\"123.0f\"/>\n"
            + "<attribute key=\"key1\" type=\"double\" value=\"123.0f\">\n"
            + "</attribute>"
            + "<attribute key1=\"key2\" type=\"double\" value=\"123\"/>\n"
            + "<attribute key=\"key3\" typ=\"double\" value=\"123\"/>\n"
            + "<attribute key=\"key4\" type=\"double\" valu=\"123\"/>\n"
            + "<attribute key=\"key5\" type=\"doubl\" value=\"123\"/>\n"
            + "<attribute key=\"key6\" type=\"double\" value=\"123\" append=\"append1\"/>\n"
            + "<attribute key=\"key7\" type=\"double\" value=\"abcd\"/>\n"
            + "<attribute key=\"key8\" type=\"double\" value=\"123\"/>\n"
            + "<attribute key=\"key9\" type=\"Double\" value=\"1234\"/>\n"
            + "<attribute key=\"key8\" type=\"double\" value=\"1234\"/>\n"
            + ATTRIBUTE_END;
        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        assertEquals(123f, attributeCollection.getDouble("key0"), 0.0000001f);
        assertEquals(123f, attributeCollection.getDouble("key1"), 0.0000001f);
        assertEquals(0f, attributeCollection.getDouble("key2"), 0.0000001f);
        assertEquals(0f, attributeCollection.getDouble("key3"), 0.0000001f);
        assertEquals(0f, attributeCollection.getDouble("key4"), 0.0000001f);
        assertEquals(0f, attributeCollection.getDouble("key5"), 0.0000001f);
        assertEquals(0f, attributeCollection.getDouble("key6"), 0.0000001f);
        assertEquals(0f, attributeCollection.getDouble("key7"), 0.0000001f);
        assertEquals(1234f, attributeCollection.getDouble("key8"), 0.0000001f);
        assertEquals(1234f, attributeCollection.getDouble("key9"), 0.0000001f);
        assertEquals(100f, attributeCollection.getDouble("key10", 100f), 0.0000001f);
    }

    @Test
    public void baseCharObjectAttribute() {
        String fileInfo = XML_START
            + ATTRIBUTE_BEGIN
            + "<attribute key=\"key0\" type=\"char\" value=\"a\"/>\n"
            + "<attribute key=\"key1\" type=\"char\" value=\"a\">\n"
            + "</attribute>"
            + "<attribute key1=\"key2\" type=\"char\" value=\"b\"/>\n"
            + "<attribute key=\"key3\" typ=\"char\" value=\"c\"/>\n"
            + "<attribute key=\"key4\" type=\"char\" valu=\"d\"/>\n"
            + "<attribute key=\"key5\" type=\"cha\" value=\"e\"/>\n"
            + "<attribute key=\"key6\" type=\"char\" value=\"f\" append=\"append1\"/>\n"
            + "<attribute key=\"key7\" type=\"char\" value=\"g1\"/>\n"
            + "<attribute key=\"key8\" type=\"char\" value=\"h\"/>\n"
            + "<attribute key=\"key9\" type=\"Char\" value=\"i\"/>\n"
            + "<attribute key=\"key8\" type=\"char\" value=\"j\"/>\n"
            + ATTRIBUTE_END;
        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        assertEquals('a', attributeCollection.getChar("key0"));
        assertEquals('a', attributeCollection.getChar("key1"));
        assertEquals('\0', attributeCollection.getChar("key2"));
        assertEquals('\0', attributeCollection.getChar("key3"));
        assertEquals('\0', attributeCollection.getChar("key4"));
        assertEquals('\0', attributeCollection.getChar("key5"));
        assertEquals('\0', attributeCollection.getChar("key6"));
        assertEquals('\0', attributeCollection.getChar("key7"));
        assertEquals('j', attributeCollection.getChar("key8"));
        assertEquals('i', attributeCollection.getChar("key9"));
        assertEquals('z', attributeCollection.getChar("key10", 'z'));
    }

    @Test
    public void baseStringObjectAttribute() {
        String fileInfo = XML_START
            + ATTRIBUTE_BEGIN
            + "<attribute key=\"key0\" type=\"string\" value=\"value1\"/>\n"
            + "<attribute key=\"key1\" type=\"string\" value=\"value1\">\n"
            + "</attribute>"
            + "<attribute key1=\"key2\" type=\"string\" value=\"value2\"/>\n"
            + "<attribute key=\"key3\" typ=\"string\" value=\"value3\"/>\n"
            + "<attribute key=\"key4\" type=\"string\" valu=\"value4\"/>\n"
            + "<attribute key=\"key5\" type=\"strin\" value=\"value5\"/>\n"
            + "<attribute key=\"key6\" type=\"string\" value=\"value6\" append=\"append1\"/>\n"
            + "<attribute key=\"key7\" type=\"string\" value=\"1234\"/>\n"
            + "<attribute key=\"key8\" type=\"string\" value=\"value8\"/>\n"
            + "<attribute key=\"key9\" type=\"String\" value=\"2345\"/>\n"
            + "<attribute key=\"key8\" type=\"string\" value=\"value9\"/>\n"
            + "<attribute key=\"key10\" type=\"string\" value=\"\"/>\n"
            + ATTRIBUTE_END;
        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        assertEquals("value1", attributeCollection.getString("key0"));
        assertEquals("value1", attributeCollection.getString("key1"));
        assertNull(attributeCollection.getString("key2"));
        assertNull(attributeCollection.getString("key3"));
        assertNull(attributeCollection.getString("key4"));
        assertNull(attributeCollection.getString("key5"));
        assertNull(attributeCollection.getString("key6"));
        assertEquals("1234", attributeCollection.getString("key7"));
        assertEquals("value9", attributeCollection.getString("key8"));
        assertEquals("2345", attributeCollection.getString("key9"));
        assertEquals("", attributeCollection.getString("key10"));
        assertEquals("hello world", attributeCollection.getString("key11", "hello world"));
    }

    @Test
    public void customObjectAttribute() {
        String fileInfo = XML_START
            + ATTRIBUTE_BEGIN
            + "<attribute key=\"key0\" type=\"String\" value=\"value1\" />\n"
            + "<attribute key=\"key1\" type=\"PersonAttribute\" name=\"li\" age=\"20\""
            + " high=\"180\" weight=\"150\" />\n"
            + "<attribute key=\"key11\" type=\"PersonAttribute\" name=\"wang\" age=\"24\""
            + " high=\"188\" weight=\"140\">\n"
            + "</attribute>"
            + "<attribute key1=\"key2\" type=\"PersonAttribute\" name=\"li\" age=\"20\""
            + " high=\"180\" weight=\"150\" />\n"
            + "<attribute key=\"key3\" typ=\"PersonAttribute\" name=\"li\" age=\"20\""
            + " high=\"180\" weight=\"150\" />\n"
            + "<attribute key=\"key4\" type=\"PersonAttribute\" nam=\"li\" age=\"20\""
            + " high=\"180\" weight=\"150\" />\n"
            + "<attribute key=\"key44\" type=\"PersonAttribute\" name=\"wang\" age=\"32\""
            + " high=\"180\" weight=\"140\">\n"
            + "</attribute>"
            + "<attribute key=\"key5\" type=\"PersonConfi\" name=\"li\" age=\"20\""
            + " high=\"180\" weight=\"150\" />\n"
            + "<attribute key=\"key6\" type=\"PersonAttribute\" name=\"li\" age=\"20\""
            + " high=\"180\" weight=\"150\" append=\"append1\" />\n"
            + "<attribute key=\"key7\" type=\"PersonAttribute\" name=\"li\" age=\"hello\""
            + " high=\"180\" weight=\"150\" />\n"
            + "<attribute key=\"key8\" type=\"PersonAttribute\" name=\"li\" age=\"20\""
            + " high=\"180\" weight=\"150\" />\n"
            + "<attribute key=\"key44\" type=\"PersonAttribute\" name=\"li\" age=\"32\""
            + " high=\"200\" weight=\"160\">\n"
            + "</attribute>"
            + "<attribute key=\"key8\" type=\"PersonAttribute\" name=\"wang\" age=\"24\""
            + " high=\"200\" weight=\"150\" />\n"
            + "<attribute key=\"key44\" type=\"PersonAttribute\" name=\"wang\" age=\"24\""
            + " high=\"188\" weight=\"140\">\n"
            + "</attribute>"
            + ATTRIBUTE_END;
        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        assertEquals("value1", attributeCollection.getString("key0"));
        PersonAttribute result = attributeCollection.getAttribute("key1");
        assertEquals("li", result.getName());
        assertEquals(20, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        result = attributeCollection.getAttribute("key11");
        assertEquals("wang", result.getName());
        assertEquals(24, result.getAge());
        assertEquals(188, result.getHigh());
        assertEquals(140, result.getWeight());
        result = attributeCollection.getAttribute("key3");
        assertNull(result);
        result = attributeCollection.getAttribute("key4");
        assertNull(result.getName());
        assertEquals(20, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        result = attributeCollection.getAttribute("key5");
        assertNull(result);
        result = attributeCollection.getAttribute("key6");
        assertEquals("li", result.getName());
        assertEquals(20, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        result = attributeCollection.getAttribute("key7");
        assertEquals("li", result.getName());
        assertEquals(0, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        result = attributeCollection.getAttribute("key8");
        assertEquals("wang", result.getName());
        assertEquals(24, result.getAge());
        assertEquals(200, result.getHigh());
        assertEquals(150, result.getWeight());
        result = attributeCollection.getAttribute("key44");
        assertEquals("wang", result.getName());
        assertEquals(24, result.getAge());
        assertEquals(188, result.getHigh());
        assertEquals(140, result.getWeight());
        result = attributeCollection.getAttribute("key10",
            new PersonAttribute("li", 30, 180, 150, false));
        assertEquals("li", result.getName());
        assertEquals(30, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        assertFalse(result.getGender());
    }

}
