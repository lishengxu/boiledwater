package com.deepindex.attributeset;

import android.content.Context;
import android.util.ArrayMap;

import androidx.test.platform.app.InstrumentationRegistry;

import com.deepindex.attributeset.attributepath.PersonAttribute;

import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class MapAttributeTest {
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
    public void baseBooleanListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_map key=\"key0\" type=\"boolean\" >\n"
                + "</attribute_map>\n"
                + "<attribute_map key=\"key00\" type=\"boolean\" />\n"
                + "<attribute_map key=\"key1\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1001\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" >\n"
                + "</sub>"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1002\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" >\n"
                + "</sub>"
                + "</attribute_map>"
                + "<attribute_map key1=\"key2\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key3\" typ=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key4\" type=\"boolean\" append=\"append1\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key6\" type=\"boolean\" >\n"
                + "<su key=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key7\" type=\"boolean\" >\n"
                + "<sub ke=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key8\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" valu=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key9\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<su key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key10\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<sub ke=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key11\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" valu=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key12\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key13\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"\" />\n"
                + "<sub key=\"subkey2\" value=\"true\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key14\" type=\"boolea\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key15\" type=\"Boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"false\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayMap<String, Boolean> map = attributeCollection.getBooleanArrayMap("key0");
        assertTrue(map.isEmpty());
        map = attributeCollection.getBooleanArrayMap("key00");
        assertTrue(map.isEmpty());
        map = attributeCollection.getBooleanArrayMap("key1");
        Boolean value = map.get("subkey1");
        assertNotNull(value);
        assertTrue(value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertFalse(value);
        map = attributeCollection.getBooleanArrayMap("key1001");
        value = map.get("subkey1");
        assertNotNull(value);
        assertTrue(value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertFalse(value);
        map = attributeCollection.getBooleanArrayMap("key1002");
        value = map.get("subkey1");
        assertNotNull(value);
        assertTrue(value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertFalse(value);
        map = attributeCollection.getBooleanArrayMap("key2");
        assertNull(map);
        map = attributeCollection.getBooleanArrayMap("key3");
        assertNull(map);
        map = attributeCollection.getBooleanArrayMap("key4");
        assertNull(map);
        map = attributeCollection.getBooleanArrayMap("key5");
        value = map.get("subkey1");
        assertNotNull(value);
        assertFalse(value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertFalse(value);
        map = attributeCollection.getBooleanArrayMap("key6");
        assertNull(map);
        map = attributeCollection.getBooleanArrayMap("key7");
        assertNull(map);
        map = attributeCollection.getBooleanArrayMap("key8");
        assertNull(map);
        map = attributeCollection.getBooleanArrayMap("key9");
        assertNull(map);
        map = attributeCollection.getBooleanArrayMap("key10");
        assertNull(map);
        map = attributeCollection.getBooleanArrayMap("key11");
        assertNull(map);
        map = attributeCollection.getBooleanArrayMap("key12");
        assertNull(map);
        map = attributeCollection.getBooleanArrayMap("key13");
        assertNull(map);
        map = attributeCollection.getBooleanArrayMap("key14");
        assertNull(map);
        map = attributeCollection.getBooleanArrayMap("key15");
        value = map.get("subkey1");
        assertNotNull(value);
        assertTrue(value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertFalse(value);
        ArrayMap<String, Boolean> defaultValue = new ArrayMap<>();
        defaultValue.put("subkey1", false);
        defaultValue.put("subkey2", false);
        map = attributeCollection.getBooleanArrayMap("key14", defaultValue);
        value = map.get("subkey1");
        assertNotNull(value);
        assertFalse(value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertFalse(value);
    }

    @Test
    public void baseByteListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_map key=\"key0\" type=\"byte\" >\n"
                + "</attribute_map>\n"
                + "<attribute_map key=\"key00\" type=\"byte\" />\n"
                + "<attribute_map key=\"key1\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1001\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" >\n"
                + "</sub>"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1002\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" >\n"
                + "</sub>"
                + "</attribute_map>"
                + "<attribute_map key1=\"key2\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key3\" typ=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key4\" type=\"byte\" append=\"append1\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key6\" type=\"byte\" >\n"
                + "<su key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key7\" type=\"byte\" >\n"
                + "<sub ke=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key8\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" valu=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key9\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<su key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key10\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub ke=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key11\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" valu=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key12\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key13\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key14\" type=\"byt\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key15\" type=\"Byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"12\" />\n"
                + "</attribute_map>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayMap<String, Byte> map = attributeCollection.getByteArrayMap("key0");
        assertTrue(map.isEmpty());
        map = attributeCollection.getByteArrayMap("key00");
        assertTrue(map.isEmpty());
        map = attributeCollection.getByteArrayMap("key1");
        Byte value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(12, value.byteValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(32, value.byteValue());
        map = attributeCollection.getByteArrayMap("key1001");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(12, value.byteValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(32, value.byteValue());
        map = attributeCollection.getByteArrayMap("key1002");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(12, value.byteValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(32, value.byteValue());
        map = attributeCollection.getByteArrayMap("key2");
        assertNull(map);
        map = attributeCollection.getByteArrayMap("key3");
        assertNull(map);
        map = attributeCollection.getByteArrayMap("key4");
        assertNull(map);
        map = attributeCollection.getByteArrayMap("key5");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(12, value.byteValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(12, value.byteValue());
        map = attributeCollection.getByteArrayMap("key6");
        assertNull(map);
        map = attributeCollection.getByteArrayMap("key7");
        assertNull(map);
        map = attributeCollection.getByteArrayMap("key8");
        assertNull(map);
        map = attributeCollection.getByteArrayMap("key9");
        assertNull(map);
        map = attributeCollection.getByteArrayMap("key10");
        assertNull(map);
        map = attributeCollection.getByteArrayMap("key11");
        assertNull(map);
        map = attributeCollection.getByteArrayMap("key12");
        assertNull(map);
        map = attributeCollection.getByteArrayMap("key13");
        assertNull(map);
        map = attributeCollection.getByteArrayMap("key14");
        assertNull(map);
        map = attributeCollection.getByteArrayMap("key15");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(12, value.byteValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(32, value.byteValue());
        ArrayMap<String, Byte> defaultValue = new ArrayMap<>();
        defaultValue.put("subkey1", (byte) 11);
        defaultValue.put("subkey2", (byte) 22);
        map = attributeCollection.getByteArrayMap("key14", defaultValue);
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(11, value.byteValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(22, value.byteValue());
    }

    @Test
    public void baseShortListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_map key=\"key0\" type=\"short\" >\n"
                + "</attribute_map>\n"
                + "<attribute_map key=\"key00\" type=\"short\" />\n"
                + "<attribute_map key=\"key1\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1001\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" >\n"
                + "</sub>"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1002\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" >\n"
                + "</sub>"
                + "</attribute_map>"
                + "<attribute_map key1=\"key2\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key3\" typ=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key4\" type=\"short\" append=\"append1\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key6\" type=\"short\" >\n"
                + "<su key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key7\" type=\"short\" >\n"
                + "<sub ke=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key8\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" valu=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key9\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<su key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key10\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub ke=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key11\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" valu=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key12\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key13\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key14\" type=\"shor\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key15\" type=\"Short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"123\" />\n"
                + "</attribute_map>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayMap<String, Short> map = attributeCollection.getShortArrayMap("key0");
        assertTrue(map.isEmpty());
        map = attributeCollection.getShortArrayMap("key00");
        assertTrue(map.isEmpty());
        map = attributeCollection.getShortArrayMap("key1");
        Short value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321, value.shortValue());
        map = attributeCollection.getShortArrayMap("key1001");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321, value.shortValue());
        map = attributeCollection.getShortArrayMap("key1002");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321, value.shortValue());
        map = attributeCollection.getShortArrayMap("key2");
        assertNull(map);
        map = attributeCollection.getShortArrayMap("key3");
        assertNull(map);
        map = attributeCollection.getShortArrayMap("key4");
        assertNull(map);
        map = attributeCollection.getShortArrayMap("key5");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        map = attributeCollection.getShortArrayMap("key6");
        assertNull(map);
        map = attributeCollection.getShortArrayMap("key7");
        assertNull(map);
        map = attributeCollection.getShortArrayMap("key8");
        assertNull(map);
        map = attributeCollection.getShortArrayMap("key9");
        assertNull(map);
        map = attributeCollection.getShortArrayMap("key10");
        assertNull(map);
        map = attributeCollection.getShortArrayMap("key11");
        assertNull(map);
        map = attributeCollection.getShortArrayMap("key12");
        assertNull(map);
        map = attributeCollection.getShortArrayMap("key13");
        assertNull(map);
        map = attributeCollection.getShortArrayMap("key14");
        assertNull(map);
        map = attributeCollection.getShortArrayMap("key15");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321, value.shortValue());
        ArrayMap<String, Short> defaultValue = new ArrayMap<>();
        defaultValue.put("subkey1", (short) 111);
        defaultValue.put("subkey2", (short) 222);
        map = attributeCollection.getShortArrayMap("key14", defaultValue);
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(111, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(222, value.shortValue());
    }

    @Test
    public void baseIntListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_map key=\"key0\" type=\"int\" >\n"
                + "</attribute_map>\n"
                + "<attribute_map key=\"key00\" type=\"int\" />\n"
                + "<attribute_map key=\"key1\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1002\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" >\n"
                + "</sub>"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1001\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" >\n"
                + "</sub>"
                + "</attribute_map>"
                + "<attribute_map key1=\"key2\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key3\" typ=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key4\" type=\"int\" append=\"append1\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key6\" type=\"int\" >\n"
                + "<su key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key7\" type=\"int\" >\n"
                + "<sub ke=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key8\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" valu=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key9\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<su key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key10\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub ke=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key11\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" valu=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key12\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key13\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key14\" type=\"in\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key15\" type=\"Int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"123\" />\n"
                + "</attribute_map>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayMap<String, Integer> map = attributeCollection.getIntegerArrayMap("key0");
        assertTrue(map.isEmpty());
        map = attributeCollection.getIntegerArrayMap("key00");
        assertTrue(map.isEmpty());
        map = attributeCollection.getIntegerArrayMap("key1");
        Integer value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321, value.shortValue());
        map = attributeCollection.getIntegerArrayMap("key1001");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321, value.shortValue());
        map = attributeCollection.getIntegerArrayMap("key1002");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321, value.shortValue());
        map = attributeCollection.getIntegerArrayMap("key2");
        assertNull(map);
        map = attributeCollection.getIntegerArrayMap("key3");
        assertNull(map);
        map = attributeCollection.getIntegerArrayMap("key4");
        assertNull(map);
        map = attributeCollection.getIntegerArrayMap("key5");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        map = attributeCollection.getIntegerArrayMap("key6");
        assertNull(map);
        map = attributeCollection.getIntegerArrayMap("key7");
        assertNull(map);
        map = attributeCollection.getIntegerArrayMap("key8");
        assertNull(map);
        map = attributeCollection.getIntegerArrayMap("key9");
        assertNull(map);
        map = attributeCollection.getIntegerArrayMap("key10");
        assertNull(map);
        map = attributeCollection.getIntegerArrayMap("key11");
        assertNull(map);
        map = attributeCollection.getIntegerArrayMap("key12");
        assertNull(map);
        map = attributeCollection.getIntegerArrayMap("key13");
        assertNull(map);
        map = attributeCollection.getIntegerArrayMap("key14");
        assertNull(map);
        map = attributeCollection.getIntegerArrayMap("key15");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321, value.shortValue());
        ArrayMap<String, Integer> defaultValue = new ArrayMap<>();
        defaultValue.put("subkey1", 111);
        defaultValue.put("subkey2", 222);
        map = attributeCollection.getIntegerArrayMap("key14", defaultValue);
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(111, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(222, value.shortValue());
    }

    @Test
    public void baseLongListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_map key=\"key0\" type=\"long\" >\n"
                + "</attribute_map>\n"
                + "<attribute_map key=\"key00\" type=\"long\" />\n"
                + "<attribute_map key=\"key1\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1001\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" >\n"
                + "</sub>"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1002\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" >\n"
                + "</sub>"
                + "</attribute_map>"
                + "<attribute_map key1=\"key2\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key3\" typ=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key4\" type=\"long\" append=\"append1\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key6\" type=\"long\" >\n"
                + "<su key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key7\" type=\"long\" >\n"
                + "<sub ke=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key8\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" valu=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key9\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<su key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key10\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub ke=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key11\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" valu=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key12\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key13\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key14\" type=\"lon\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key15\" type=\"Long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"123\" />\n"
                + "</attribute_map>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayMap<String, Long> map = attributeCollection.getLongArrayMap("key0");
        assertTrue(map.isEmpty());
        map = attributeCollection.getLongArrayMap("key00");
        assertTrue(map.isEmpty());
        map = attributeCollection.getLongArrayMap("key1");
        Long value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321, value.shortValue());
        map = attributeCollection.getLongArrayMap("key1001");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321, value.shortValue());
        map = attributeCollection.getLongArrayMap("key1002");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321, value.shortValue());
        map = attributeCollection.getLongArrayMap("key2");
        assertNull(map);
        map = attributeCollection.getLongArrayMap("key3");
        assertNull(map);
        map = attributeCollection.getLongArrayMap("key4");
        assertNull(map);
        map = attributeCollection.getLongArrayMap("key5");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        map = attributeCollection.getLongArrayMap("key6");
        assertNull(map);
        map = attributeCollection.getLongArrayMap("key7");
        assertNull(map);
        map = attributeCollection.getLongArrayMap("key8");
        assertNull(map);
        map = attributeCollection.getLongArrayMap("key9");
        assertNull(map);
        map = attributeCollection.getLongArrayMap("key10");
        assertNull(map);
        map = attributeCollection.getLongArrayMap("key11");
        assertNull(map);
        map = attributeCollection.getLongArrayMap("key12");
        assertNull(map);
        map = attributeCollection.getLongArrayMap("key13");
        assertNull(map);
        map = attributeCollection.getLongArrayMap("key14");
        assertNull(map);
        map = attributeCollection.getLongArrayMap("key15");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321, value.shortValue());
        ArrayMap<String, Long> defaultValue = new ArrayMap<>();
        defaultValue.put("subkey1", (long) 111);
        defaultValue.put("subkey2", (long) 222);
        map = attributeCollection.getLongArrayMap("key14", defaultValue);
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(111, value.shortValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(222, value.shortValue());
    }

    @Test
    public void baseFloatListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_map key=\"key0\" type=\"float\" >\n"
                + "</attribute_map>\n"
                + "<attribute_map key=\"key00\" type=\"float\" />\n"
                + "<attribute_map key=\"key1\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1001\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" >\n"
                + "</sub>"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1002\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" >\n"
                + "</sub>"
                + "</attribute_map>"
                + "<attribute_map key1=\"key2\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key3\" typ=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key4\" type=\"float\" append=\"append1\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key6\" type=\"float\" >\n"
                + "<su key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key7\" type=\"float\" >\n"
                + "<sub ke=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key8\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" valu=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key9\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<su key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key10\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub ke=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key11\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" valu=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key12\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key13\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key14\" type=\"floa\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key15\" type=\"Float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"123.0f\" />\n"
                + "</attribute_map>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayMap<String, Float> map = attributeCollection.getFloatArrayMap("key0");
        assertTrue(map.isEmpty());
        map = attributeCollection.getFloatArrayMap("key00");
        assertTrue(map.isEmpty());
        map = attributeCollection.getFloatArrayMap("key1");
        Float value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123.0f, value, 0.0000001);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321f, value, 0.0000001);
        map = attributeCollection.getFloatArrayMap("key1001");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123.0f, value, 0.0000001);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321f, value, 0.0000001);
        map = attributeCollection.getFloatArrayMap("key1002");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123.0f, value, 0.0000001);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321f, value, 0.0000001);
        map = attributeCollection.getFloatArrayMap("key2");
        assertNull(map);
        map = attributeCollection.getFloatArrayMap("key3");
        assertNull(map);
        map = attributeCollection.getFloatArrayMap("key4");
        assertNull(map);
        map = attributeCollection.getFloatArrayMap("key5");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123.0f, value, 0.0000001);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(123.0f, value, 0.0000001);
        map = attributeCollection.getFloatArrayMap("key6");
        assertNull(map);
        map = attributeCollection.getFloatArrayMap("key7");
        assertNull(map);
        map = attributeCollection.getFloatArrayMap("key8");
        assertNull(map);
        map = attributeCollection.getFloatArrayMap("key9");
        assertNull(map);
        map = attributeCollection.getFloatArrayMap("key10");
        assertNull(map);
        map = attributeCollection.getFloatArrayMap("key11");
        assertNull(map);
        map = attributeCollection.getFloatArrayMap("key12");
        assertNull(map);
        map = attributeCollection.getFloatArrayMap("key13");
        assertNull(map);
        map = attributeCollection.getFloatArrayMap("key14");
        assertNull(map);
        map = attributeCollection.getFloatArrayMap("key15");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123.0f, value, 0.0000001);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321f, value, 0.0000001);
        ArrayMap<String, Float> defaultValue = new ArrayMap<>();
        defaultValue.put("subkey1", 111.0f);
        defaultValue.put("subkey2", 222.0f);
        map = attributeCollection.getFloatArrayMap("key14", defaultValue);
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(111.0f, value, 0.0000001);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(222.0f, value, 0.0000001);
    }

    @Test
    public void baseDoubleListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_map key=\"key0\" type=\"double\" >\n"
                + "</attribute_map>\n"
                + "<attribute_map key=\"key00\" type=\"double\" />\n"
                + "<attribute_map key=\"key1\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1001\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" >\n"
                + "</sub>"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1002\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" >\n"
                + "</sub>"
                + "</attribute_map>"
                + "<attribute_map key1=\"key2\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key3\" typ=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key4\" type=\"double\" append=\"append1\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key6\" type=\"double\" >\n"
                + "<su key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key7\" type=\"double\" >\n"
                + "<sub ke=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key8\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" valu=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key9\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<su key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key10\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub ke=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key11\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" valu=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key12\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key13\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key14\" type=\"doubl\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key15\" type=\"Double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"123.0f\" />\n"
                + "</attribute_map>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayMap<String, Double> map = attributeCollection.getDoubleArrayMap("key0");
        assertTrue(map.isEmpty());
        map = attributeCollection.getDoubleArrayMap("key00");
        assertTrue(map.isEmpty());
        map = attributeCollection.getDoubleArrayMap("key1");
        Double value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123.0f, value, 0.0000001);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321f, value, 0.0000001);
        map = attributeCollection.getDoubleArrayMap("key1001");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123.0f, value, 0.0000001);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321f, value, 0.0000001);
        map = attributeCollection.getDoubleArrayMap("key1002");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123.0f, value, 0.0000001);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321f, value, 0.0000001);
        map = attributeCollection.getDoubleArrayMap("key2");
        assertNull(map);
        map = attributeCollection.getDoubleArrayMap("key3");
        assertNull(map);
        map = attributeCollection.getDoubleArrayMap("key4");
        assertNull(map);
        map = attributeCollection.getDoubleArrayMap("key5");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123.0f, value, 0.0000001);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(123.0f, value, 0.0000001);
        map = attributeCollection.getDoubleArrayMap("key6");
        assertNull(map);
        map = attributeCollection.getDoubleArrayMap("key7");
        assertNull(map);
        map = attributeCollection.getDoubleArrayMap("key8");
        assertNull(map);
        map = attributeCollection.getDoubleArrayMap("key9");
        assertNull(map);
        map = attributeCollection.getDoubleArrayMap("key10");
        assertNull(map);
        map = attributeCollection.getDoubleArrayMap("key11");
        assertNull(map);
        map = attributeCollection.getDoubleArrayMap("key12");
        assertNull(map);
        map = attributeCollection.getDoubleArrayMap("key13");
        assertNull(map);
        map = attributeCollection.getDoubleArrayMap("key14");
        assertNull(map);
        map = attributeCollection.getDoubleArrayMap("key15");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(123.0f, value, 0.0000001);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(321f, value, 0.0000001);
        ArrayMap<String, Double> defaultValue = new ArrayMap<>();
        defaultValue.put("subkey1", (double) 111.0f);
        defaultValue.put("subkey2", (double) 222.0f);
        map = attributeCollection.getDoubleArrayMap("key14", defaultValue);
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals(111.0f, value, 0.0000001);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals(222.0f, value, 0.0000001);
    }


    @Test
    public void baseCharListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_map key=\"key0\" type=\"char\" >\n"
                + "</attribute_map>\n"
                + "<attribute_map key=\"key00\" type=\"char\" />\n"
                + "<attribute_map key=\"key1\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1001\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" >\n"
                + "</sub>"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1002\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" >\n"
                + "</sub>"
                + "</attribute_map>"
                + "<attribute_map key1=\"key2\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key3\" typ=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key4\" type=\"char\" append=\"append1\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key6\" type=\"char\" >\n"
                + "<su key=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key7\" type=\"char\" >\n"
                + "<sub ke=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key8\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" valu=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key9\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<su key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key10\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<sub ke=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key11\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" valu=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key12\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key13\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key14\" type=\"cha\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key15\" type=\"Char\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"b\" />\n"
                + "<sub key=\"subkey2\" value=\"2\" />\n"
                + "</attribute_map>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayMap<String, Character> map = attributeCollection.getCharacterArrayMap("key0");
        assertTrue(map.isEmpty());
        map = attributeCollection.getCharacterArrayMap("key00");
        assertTrue(map.isEmpty());
        map = attributeCollection.getCharacterArrayMap("key1");
        Character value = map.get("subkey1");
        assertNotNull(value);
        assertEquals('a', value.charValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals('0', value.charValue());
        map = attributeCollection.getCharacterArrayMap("key1001");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals('a', value.charValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals('0', value.charValue());
        map = attributeCollection.getCharacterArrayMap("key1002");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals('a', value.charValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals('0', value.charValue());
        map = attributeCollection.getCharacterArrayMap("key2");
        assertNull(map);
        map = attributeCollection.getCharacterArrayMap("key3");
        assertNull(map);
        map = attributeCollection.getCharacterArrayMap("key4");
        assertNull(map);
        map = attributeCollection.getCharacterArrayMap("key5");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals('b', value.charValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals('2', value.charValue());
        map = attributeCollection.getCharacterArrayMap("key6");
        assertNull(map);
        map = attributeCollection.getCharacterArrayMap("key7");
        assertNull(map);
        map = attributeCollection.getCharacterArrayMap("key8");
        assertNull(map);
        map = attributeCollection.getCharacterArrayMap("key9");
        assertNull(map);
        map = attributeCollection.getCharacterArrayMap("key10");
        assertNull(map);
        map = attributeCollection.getCharacterArrayMap("key11");
        assertNull(map);
        map = attributeCollection.getCharacterArrayMap("key12");
        assertNull(map);
        map = attributeCollection.getCharacterArrayMap("key13");
        assertNull(map);
        map = attributeCollection.getCharacterArrayMap("key14");
        assertNull(map);
        map = attributeCollection.getCharacterArrayMap("key15");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals('a', value.charValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals('0', value.charValue());
        ArrayMap<String, Character> defaultValue = new ArrayMap<>();
        defaultValue.put("subkey1", (char) 'x');
        defaultValue.put("subkey2", (char) 'y');
        map = attributeCollection.getCharacterArrayMap("key14", defaultValue);
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals('x', value.charValue());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals('y', value.charValue());
    }

    @Test
    public void baseStringListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_map key=\"key0\" type=\"string\" >\n"
                + "</attribute_map>\n"
                + "<attribute_map key=\"key00\" type=\"string\" />\n"
                + "<attribute_map key=\"key1\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1001\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" >\n"
                + "</sub>"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1002\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" >\n"
                + "</sub>"
                + "</attribute_map>"
                + "<attribute_map key1=\"key2\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key3\" typ=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key4\" type=\"string\" append=\"append1\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key6\" type=\"string\" >\n"
                + "<su key=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key7\" type=\"string\" >\n"
                + "<sub ke=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key8\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" valu=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key9\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<su key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key10\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<sub ke=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key11\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" valu=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key12\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key13\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key14\" type=\"strin\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key15\" type=\"String\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"1234\" />\n"
                + "<sub key=\"subkey2\" value=\"4321\" />\n"
                + "</attribute_map>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayMap<String, String> map = attributeCollection.getStringArrayMap("key0");
        assertTrue(map.isEmpty());
        map = attributeCollection.getStringArrayMap("key00");
        assertTrue(map.isEmpty());
        map = attributeCollection.getStringArrayMap("key1");
        String value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("hello", value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("world", value);
        map = attributeCollection.getStringArrayMap("key1001");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("hello", value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("world", value);
        map = attributeCollection.getStringArrayMap("key1002");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("hello", value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("world", value);
        map = attributeCollection.getStringArrayMap("key2");
        assertNull(map);
        map = attributeCollection.getStringArrayMap("key3");
        assertNull(map);
        map = attributeCollection.getStringArrayMap("key4");
        assertNull(map);
        map = attributeCollection.getStringArrayMap("key5");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("1234", value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("4321", value);
        map = attributeCollection.getStringArrayMap("key6");
        assertNull(map);
        map = attributeCollection.getStringArrayMap("key7");
        assertNull(map);
        map = attributeCollection.getStringArrayMap("key8");
        assertNull(map);
        map = attributeCollection.getStringArrayMap("key9");
        assertNull(map);
        map = attributeCollection.getStringArrayMap("key10");
        assertNull(map);
        map = attributeCollection.getStringArrayMap("key11");
        assertNull(map);
        map = attributeCollection.getStringArrayMap("key12");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("hello", value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("", value);
        map = attributeCollection.getStringArrayMap("key13");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("", value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("world", value);
        map = attributeCollection.getStringArrayMap("key14");
        assertNull(map);
        map = attributeCollection.getStringArrayMap("key15");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("hello", value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("world", value);
        ArrayMap<String, String> defaultValue = new ArrayMap<>();
        defaultValue.put("subkey1", "xxx");
        defaultValue.put("subkey2", "yyy");
        map = attributeCollection.getStringArrayMap("key14", defaultValue);
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("xxx", value);
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("yyy", value);
    }

    @Test
    public void customMapAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_map key=\"key0\" type=\"PersonAttribute\" >\n"
                + "</attribute_map>\n"
                + "<attribute_map key=\"key00\" type=\"PersonAttribute\" />\n"
                + "<attribute_map key=\"key1\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1001\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" >\n"
                + "</sub>"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key1002\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" >\n"
                + "</sub>"
                + "</attribute_map>"
                + "<attribute_map key1=\"key2\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key3\" typ=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key4\" type=\"PersonAttribute\" append=\"append1\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key6\" type=\"PersonAttribute\" >\n"
                + "<su key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key7\" type=\"PersonAttribute\" >\n"
                + "<sub ke=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key8\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" nam=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key9\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<su key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key10\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub ke=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key11\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" ag=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key12\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key13\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key14\" type=\"PersonConfi\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key5\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"zhou\" age=\"21\" high=\"181\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"huang\" age=\"22\" high=\"182\" weight=\"90\" />\n"
                + "</attribute_map>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayMap<String, PersonAttribute> map = attributeCollection.getAttributeArrayMap("key0");
        assertTrue(map.isEmpty());
        map = attributeCollection.getAttributeArrayMap("key00");
        assertTrue(map.isEmpty());
        map = attributeCollection.getAttributeArrayMap("key1");
        PersonAttribute value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("li", value.getName());
        assertEquals(20, value.getAge());
        assertEquals(180, value.getHigh());
        assertEquals(70, value.getWeight());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("wang", value.getName());
        assertEquals(21, value.getAge());
        assertEquals(185, value.getHigh());
        assertEquals(80, value.getWeight());
        map = attributeCollection.getAttributeArrayMap("key1001");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("li", value.getName());
        assertEquals(20, value.getAge());
        assertEquals(180, value.getHigh());
        assertEquals(70, value.getWeight());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("wang", value.getName());
        assertEquals(21, value.getAge());
        assertEquals(185, value.getHigh());
        assertEquals(80, value.getWeight());
        map = attributeCollection.getAttributeArrayMap("key1002");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("li", value.getName());
        assertEquals(20, value.getAge());
        assertEquals(180, value.getHigh());
        assertEquals(70, value.getWeight());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("wang", value.getName());
        assertEquals(21, value.getAge());
        assertEquals(185, value.getHigh());
        assertEquals(80, value.getWeight());
        map = attributeCollection.getAttributeArrayMap("key2");
        assertNull(map);
        map = attributeCollection.getAttributeArrayMap("key3");
        assertNull(map);
        map = attributeCollection.getAttributeArrayMap("key4");
        assertNull(map);
        map = attributeCollection.getAttributeArrayMap("key5");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("zhou", value.getName());
        assertEquals(21, value.getAge());
        assertEquals(181, value.getHigh());
        assertEquals(70, value.getWeight());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("huang", value.getName());
        assertEquals(22, value.getAge());
        assertEquals(182, value.getHigh());
        assertEquals(90, value.getWeight());
        map = attributeCollection.getAttributeArrayMap("key6");
        assertNull(map);
        map = attributeCollection.getAttributeArrayMap("key7");
        assertNull(map);
        map = attributeCollection.getAttributeArrayMap("key8");
        value = map.get("subkey1");
        assertNotNull(value);
        assertNull(value.getName());
        assertEquals(20, value.getAge());
        assertEquals(180, value.getHigh());
        assertEquals(70, value.getWeight());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("wang", value.getName());
        assertEquals(21, value.getAge());
        assertEquals(185, value.getHigh());
        assertEquals(80, value.getWeight());
        map = attributeCollection.getAttributeArrayMap("key9");
        assertNull(map);
        map = attributeCollection.getAttributeArrayMap("key10");
        assertNull(map);
        map = attributeCollection.getAttributeArrayMap("key11");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("li", value.getName());
        assertEquals(20, value.getAge());
        assertEquals(180, value.getHigh());
        assertEquals(70, value.getWeight());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("wang", value.getName());
        assertEquals(0, value.getAge());
        assertEquals(185, value.getHigh());
        assertEquals(80, value.getWeight());
        map = attributeCollection.getAttributeArrayMap("key12");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("li", value.getName());
        assertEquals(20, value.getAge());
        assertEquals(180, value.getHigh());
        assertEquals(70, value.getWeight());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("wang", value.getName());
        assertEquals(0, value.getAge());
        assertEquals(185, value.getHigh());
        assertEquals(80, value.getWeight());
        map = attributeCollection.getAttributeArrayMap("key13");
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("li", value.getName());
        assertEquals(20, value.getAge());
        assertEquals(0, value.getHigh());
        assertEquals(70, value.getWeight());
        value = map.get("subkey2");
        assertNotNull(value);
        assertEquals("wang", value.getName());
        assertEquals(21, value.getAge());
        assertEquals(185, value.getHigh());
        assertEquals(80, value.getWeight());
        map = attributeCollection.getAttributeArrayMap("key14");
        assertNull(map);
        ArrayMap<String, PersonAttribute> defaultValue = new ArrayMap<>();
        defaultValue.put("subkey1", new PersonAttribute("zhao", 10, 160, 110, false));
        defaultValue.put("subkey3", new PersonAttribute("sun", 30, 180, 150, false));
        map = attributeCollection.getAttributeArrayMap("key14", defaultValue);
        value = map.get("subkey1");
        assertNotNull(value);
        assertEquals("zhao", value.getName());
        assertEquals(10, value.getAge());
        assertEquals(160, value.getHigh());
        assertEquals(110, value.getWeight());
        assertNull(map.get("subkey2"));
        value = map.get("subkey3");
        assertNotNull(value);
        assertEquals("sun", value.getName());
        assertEquals(30, value.getAge());
        assertEquals(180, value.getHigh());
        assertEquals(150, value.getWeight());
    }
}