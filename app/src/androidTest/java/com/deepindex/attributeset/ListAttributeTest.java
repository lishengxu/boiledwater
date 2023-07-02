package com.deepindex.attributeset;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import com.deepindex.attributeset.attributepath.PersonAttribute;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class ListAttributeTest {
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
                + "<attribute_list key=\"key0\" type=\"boolean\" >\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key00\" type=\"boolean\" />\n"
                + "<attribute_list key=\"key1\" type=\"boolean\" >\n"
                + "<sub value=\"true\" />\n"
                + "<sub value=\"false\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1001\" type=\"boolean\" >\n"
                + "<sub value=\"true\" >\n"
                + "</sub>"
                + "<sub value=\"false\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1002\" type=\"boolean\" >\n"
                + "<sub value=\"true\" />\n"
                + "<sub value=\"false\" >\n"
                + "</sub>"
                + "</attribute_list>"
                + "<attribute_list key1=\"key2\" type=\"boolean\" >\n"
                + "<sub value=\"true\" />\n"
                + "<sub value=\"false\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key3\" typ=\"boolean\" >\n"
                + "<sub value=\"true\" />\n"
                + "<sub value=\"false\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key4\" type=\"boolean\" append=\"append1\" >\n"
                + "<sub value=\"true\" />\n"
                + "<sub value=\"false\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"boolean\" >\n"
                + "<sub value=\"true\" />\n"
                + "<sub value=\"false\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key6\" type=\"boolean\" >\n"
                + "<su value=\"true\" />\n"
                + "<sub value=\"false\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key7\" type=\"boolean\" >\n"
                + "<sub valu=\"true\" />\n"
                + "<sub value=\"false\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key8\" type=\"boolean\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key88\" type=\"boolean\" >\n"
                + "<sub value=\"\" />\n"
                + "<sub value=\"\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key9\" type=\"boolea\" >\n"
                + "<sub value=\"true\" />\n"
                + "<sub value=\"false\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key10\" type=\"Boolean\" >\n"
                + "<sub value=\"true\" />\n"
                + "<sub value=\"false\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"boolean\" >\n"
                + "<sub value=\"false\" />\n"
                + "<sub value=\"false\" />\n"
                + "</attribute_list>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayList<Boolean> list = attributeCollection.getBooleanArrayList("key0");
        assertTrue(list.isEmpty());
        list = attributeCollection.getBooleanArrayList("key00");
        assertTrue(list.isEmpty());
        list = attributeCollection.getBooleanArrayList("key1");
        assertTrue(list.get(0));
        assertFalse(list.get(1));
        list = attributeCollection.getBooleanArrayList("key1001");
        assertTrue(list.get(0));
        assertFalse(list.get(1));
        list = attributeCollection.getBooleanArrayList("key1002");
        assertTrue(list.get(0));
        assertFalse(list.get(1));
        list = attributeCollection.getBooleanArrayList("key2");
        assertNull(list);
        list = attributeCollection.getBooleanArrayList("key3");
        assertNull(list);
        list = attributeCollection.getBooleanArrayList("key4");
        assertNull(list);
        list = attributeCollection.getBooleanArrayList("key5");
        assertFalse(list.get(0));
        assertFalse(list.get(1));
        list = attributeCollection.getBooleanArrayList("key6");
        assertNull(list);
        list = attributeCollection.getBooleanArrayList("key7");
        assertNull(list);
        list = attributeCollection.getBooleanArrayList("key8");
        assertNull(list);
        list = attributeCollection.getBooleanArrayList("key88");
        assertNull(list);
        list = attributeCollection.getBooleanArrayList("key9");
        assertNull(list);
        list = attributeCollection.getBooleanArrayList("key10");
        assertTrue(list.get(0));
        assertFalse(list.get(1));
        ArrayList<Boolean> defaultValue = new ArrayList<>();
        defaultValue.add(false);
        defaultValue.add(false);
        list = attributeCollection.getBooleanArrayList("key8", defaultValue);
        assertFalse(list.get(0));
        assertFalse(list.get(1));
    }

    @Test
    public void baseByteListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_list key=\"key0\" type=\"byte\" >\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key00\" type=\"byte\" />\n"
                + "<attribute_list key=\"key1\" type=\"byte\" >\n"
                + "<sub value=\"12\" />\n"
                + "<sub value=\"32\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1001\" type=\"byte\" >\n"
                + "<sub value=\"12\" >\n"
                + "</sub>"
                + "<sub value=\"32\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1002\" type=\"byte\" >\n"
                + "<sub value=\"12\" />\n"
                + "<sub value=\"32\" >\n"
                + "</sub>"
                + "</attribute_list>"
                + "<attribute_list key1=\"key2\" type=\"byte\" >\n"
                + "<sub value=\"12\" />\n"
                + "<sub value=\"32\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key3\" typ=\"byte\" >\n"
                + "<sub value=\"12\" />\n"
                + "<sub value=\"32\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key4\" type=\"byte\" append=\"append1\" >\n"
                + "<sub value=\"12\" />\n"
                + "<sub value=\"32\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"byte\" >\n"
                + "<sub value=\"11\" />\n"
                + "<sub value=\"22\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key6\" type=\"byte\" >\n"
                + "<su value=\"12\" />\n"
                + "<sub value=\"32\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key7\" type=\"byte\" >\n"
                + "<sub valu=\"12\" />\n"
                + "<sub value=\"32\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key8\" type=\"byte\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key88\" type=\"byte\" >\n"
                + "<sub value=\"\" />\n"
                + "<sub value=\"\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key9\" type=\"byt\" >\n"
                + "<sub value=\"12\" />\n"
                + "<sub value=\"32\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key10\" type=\"Byte\" >\n"
                + "<sub value=\"12\" />\n"
                + "<sub value=\"32\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"byte\" >\n"
                + "<sub value=\"33\" />\n"
                + "<sub value=\"44\" />\n"
                + "</attribute_list>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayList<Byte> list = attributeCollection.getByteArrayList("key0");
        assertTrue(list.isEmpty());
        list = attributeCollection.getByteArrayList("key00");
        assertTrue(list.isEmpty());
        list = attributeCollection.getByteArrayList("key1");
        assertEquals(12, list.get(0).byteValue());
        assertEquals(32, list.get(1).byteValue());
        list = attributeCollection.getByteArrayList("key1001");
        assertEquals(12, list.get(0).byteValue());
        assertEquals(32, list.get(1).byteValue());
        list = attributeCollection.getByteArrayList("key1002");
        assertEquals(12, list.get(0).byteValue());
        assertEquals(32, list.get(1).byteValue());
        list = attributeCollection.getByteArrayList("key2");
        assertNull(list);
        list = attributeCollection.getByteArrayList("key3");
        assertNull(list);
        list = attributeCollection.getByteArrayList("key4");
        assertNull(list);
        list = attributeCollection.getByteArrayList("key5");
        assertEquals(33, list.get(0).byteValue());
        assertEquals(44, list.get(1).byteValue());
        list = attributeCollection.getByteArrayList("key6");
        assertNull(list);
        list = attributeCollection.getByteArrayList("key7");
        assertNull(list);
        list = attributeCollection.getByteArrayList("key8");
        assertNull(list);
        list = attributeCollection.getByteArrayList("key88");
        assertNull(list);
        list = attributeCollection.getByteArrayList("key9");
        assertNull(list);
        list = attributeCollection.getByteArrayList("key10");
        assertEquals(12, list.get(0).byteValue());
        assertEquals(32, list.get(1).byteValue());
        ArrayList<Byte> defaultValue = new ArrayList<>();
        defaultValue.add((byte) 11);
        defaultValue.add((byte) 22);
        defaultValue.add((byte) 33);
        list = attributeCollection.getByteArrayList("key8", defaultValue);
        assertEquals(11, list.get(0).byteValue());
        assertEquals(22, list.get(1).byteValue());
        assertEquals(33, list.get(2).byteValue());
    }

    @Test
    public void baseShortListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_list key=\"key0\" type=\"short\" >\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key00\" type=\"short\" />\n"
                + "<attribute_list key=\"key1\" type=\"short\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1001\" type=\"short\" >\n"
                + "<sub value=\"123\" >\n"
                + "</sub>"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1002\" type=\"short\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" >\n"
                + "</sub>"
                + "</attribute_list>"
                + "<attribute_list key1=\"key2\" type=\"short\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key3\" typ=\"short\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key4\" type=\"short\" append=\"append1\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"short\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key6\" type=\"short\" >\n"
                + "<su value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key7\" type=\"short\" >\n"
                + "<sub valu=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key8\" type=\"short\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key88\" type=\"short\" >\n"
                + "<sub value=\"\" />\n"
                + "<sub value=\"\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key9\" type=\"shor\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key10\" type=\"Short\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"short\" >\n"
                + "<sub value=\"111\" />\n"
                + "<sub value=\"222\" />\n"
                + "</attribute_list>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayList<Short> list = attributeCollection.getShortArrayList("key0");
        assertTrue(list.isEmpty());
        list = attributeCollection.getShortArrayList("key00");
        assertTrue(list.isEmpty());
        list = attributeCollection.getShortArrayList("key1");
        assertEquals(123, list.get(0).shortValue());
        assertEquals(321, list.get(1).shortValue());
        list = attributeCollection.getShortArrayList("key1001");
        assertEquals(123, list.get(0).shortValue());
        assertEquals(321, list.get(1).shortValue());
        list = attributeCollection.getShortArrayList("key1002");
        assertEquals(123, list.get(0).shortValue());
        assertEquals(321, list.get(1).shortValue());
        list = attributeCollection.getShortArrayList("key2");
        assertNull(list);
        list = attributeCollection.getShortArrayList("key3");
        assertNull(list);
        list = attributeCollection.getShortArrayList("key4");
        assertNull(list);
        list = attributeCollection.getShortArrayList("key5");
        assertEquals(111, list.get(0).shortValue());
        assertEquals(222, list.get(1).shortValue());
        list = attributeCollection.getShortArrayList("key6");
        assertNull(list);
        list = attributeCollection.getShortArrayList("key7");
        assertNull(list);
        list = attributeCollection.getShortArrayList("key8");
        assertNull(list);
        list = attributeCollection.getShortArrayList("key88");
        assertNull(list);
        list = attributeCollection.getShortArrayList("key9");
        assertNull(list);
        list = attributeCollection.getShortArrayList("key10");
        assertEquals(123, list.get(0).shortValue());
        assertEquals(321, list.get(1).shortValue());
        ArrayList<Short> defaultValue = new ArrayList<>();
        defaultValue.add((short) 111);
        defaultValue.add((short) 222);
        defaultValue.add((short) 333);
        list = attributeCollection.getShortArrayList("key8", defaultValue);
        assertEquals(111, list.get(0).shortValue());
        assertEquals(222, list.get(1).shortValue());
        assertEquals(333, list.get(2).shortValue());
    }

    @Test
    public void baseIntListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_list key=\"key0\" type=\"int\" >\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key00\" type=\"int\" />\n"
                + "<attribute_list key=\"key1\" type=\"int\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1001\" type=\"int\" >\n"
                + "<sub value=\"123\" >\n"
                + "</sub>"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1002\" type=\"int\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" >\n"
                + "</sub>"
                + "</attribute_list>"
                + "<attribute_list key1=\"key2\" type=\"int\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key3\" typ=\"int\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key4\" type=\"int\" append=\"append1\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"int\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key6\" type=\"int\" >\n"
                + "<su value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key7\" type=\"int\" >\n"
                + "<sub valu=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key8\" type=\"int\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key88\" type=\"int\" >\n"
                + "<sub value=\"\" />\n"
                + "<sub value=\"\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key9 \" type=\"in\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key10\" type=\"Int\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"int\" >\n"
                + "<sub value=\"111\" />\n"
                + "<sub value=\"222\" />\n"
                + "</attribute_list>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayList<Integer> list = attributeCollection.getIntegerArrayList("key0");
        assertTrue(list.isEmpty());
        list = attributeCollection.getIntegerArrayList("key00");
        assertTrue(list.isEmpty());
        list = attributeCollection.getIntegerArrayList("key1");
        assertEquals(123, list.get(0).intValue());
        assertEquals(321, list.get(1).intValue());
        list = attributeCollection.getIntegerArrayList("key1001");
        assertEquals(123, list.get(0).intValue());
        assertEquals(321, list.get(1).intValue());
        list = attributeCollection.getIntegerArrayList("key1002");
        assertEquals(123, list.get(0).intValue());
        assertEquals(321, list.get(1).intValue());
        list = attributeCollection.getIntegerArrayList("key2");
        assertNull(list);
        list = attributeCollection.getIntegerArrayList("key3");
        assertNull(list);
        list = attributeCollection.getIntegerArrayList("key4");
        assertNull(list);
        list = attributeCollection.getIntegerArrayList("key5");
        assertEquals(111, list.get(0).intValue());
        assertEquals(222, list.get(1).intValue());
        list = attributeCollection.getIntegerArrayList("key6");
        assertNull(list);
        list = attributeCollection.getIntegerArrayList("key7");
        assertNull(list);
        list = attributeCollection.getIntegerArrayList("key8");
        assertNull(list);
        list = attributeCollection.getIntegerArrayList("key88");
        assertNull(list);
        list = attributeCollection.getIntegerArrayList("key9");
        assertNull(list);
        list = attributeCollection.getIntegerArrayList("key10");
        assertEquals(123, list.get(0).intValue());
        assertEquals(321, list.get(1).intValue());
        ArrayList<Integer> defaultValue = new ArrayList<>();
        defaultValue.add(111);
        defaultValue.add(222);
        defaultValue.add(333);
        list = attributeCollection.getIntegerArrayList("key8", defaultValue);
        assertEquals(111, list.get(0).intValue());
        assertEquals(222, list.get(1).intValue());
        assertEquals(333, list.get(2).intValue());
    }

    @Test
    public void baseLongListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_list key=\"key0\" type=\"long\" >\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key00\" type=\"long\" />\n"
                + "<attribute_list key=\"key1\" type=\"long\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1001\" type=\"long\" >\n"
                + "<sub value=\"123\" >\n"
                + "</sub>"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1002\" type=\"long\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" >\n"
                + "</sub>"
                + "</attribute_list>"
                + "<attribute_list key1=\"key2\" type=\"long\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key3\" typ=\"long\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key4\" type=\"long\" append=\"append1\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"long\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key6\" type=\"long\" >\n"
                + "<su value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key7\" type=\"long\" >\n"
                + "<sub valu=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key8\" type=\"long\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key88\" type=\"long\" >\n"
                + "<sub value=\"\" />\n"
                + "<sub value=\"\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key9\" type=\"lon\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key10\" type=\"Long\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"long\" >\n"
                + "<sub value=\"111\" />\n"
                + "<sub value=\"222\" />\n"
                + "</attribute_list>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayList<Long> list = attributeCollection.getLongArrayList("key0");
        assertTrue(list.isEmpty());
        list = attributeCollection.getLongArrayList("key00");
        assertTrue(list.isEmpty());
        list = attributeCollection.getLongArrayList("key1");
        assertEquals(123L, list.get(0).longValue());
        assertEquals(321L, list.get(1).longValue());
        list = attributeCollection.getLongArrayList("key1001");
        assertEquals(123L, list.get(0).longValue());
        assertEquals(321L, list.get(1).longValue());
        list = attributeCollection.getLongArrayList("key1002");
        assertEquals(123L, list.get(0).longValue());
        assertEquals(321L, list.get(1).longValue());
        list = attributeCollection.getLongArrayList("key2");
        assertNull(list);
        list = attributeCollection.getLongArrayList("key3");
        assertNull(list);
        list = attributeCollection.getLongArrayList("key4");
        assertNull(list);
        list = attributeCollection.getLongArrayList("key5");
        assertEquals(111L, list.get(0).longValue());
        assertEquals(222L, list.get(1).longValue());
        list = attributeCollection.getLongArrayList("key6");
        assertNull(list);
        list = attributeCollection.getLongArrayList("key7");
        assertNull(list);
        list = attributeCollection.getLongArrayList("key8");
        assertNull(list);
        list = attributeCollection.getLongArrayList("key88");
        assertNull(list);
        list = attributeCollection.getLongArrayList("key9");
        assertNull(list);
        list = attributeCollection.getLongArrayList("key10");
        assertEquals(123, list.get(0).longValue());
        assertEquals(321, list.get(1).longValue());
        ArrayList<Long> defaultValue = new ArrayList<>();
        defaultValue.add(111L);
        defaultValue.add(222L);
        defaultValue.add(333L);
        list = attributeCollection.getLongArrayList("key8", defaultValue);
        assertEquals(111, list.get(0).longValue());
        assertEquals(222, list.get(1).longValue());
        assertEquals(333, list.get(2).longValue());
    }

    @Test
    public void baseFloatListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_list key=\"key0\" type=\"float\" >\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key00\" type=\"float\" />\n"
                + "<attribute_list key=\"key1\" type=\"float\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1001\" type=\"float\" >\n"
                + "<sub value=\"123.0f\" >\n"
                + "</sub>"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1002\" type=\"float\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" >\n"
                + "</sub>"
                + "</attribute_list>"
                + "<attribute_list key1=\"key2\" type=\"float\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key3\" typ=\"float\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key4\" type=\"float\" append=\"append1\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"float\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key6\" type=\"float\" >\n"
                + "<su value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key7\" type=\"float\" >\n"
                + "<sub valu=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key8\" type=\"float\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key88\" type=\"float\" >\n"
                + "<sub value=\"\" />\n"
                + "<sub value=\"\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key9\" type=\"floa\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key10\" type=\"Float\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"float\" >\n"
                + "<sub value=\"111.0f\" />\n"
                + "<sub value=\"222f\" />\n"
                + "</attribute_list>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayList<Float> list = attributeCollection.getFloatArrayList("key0");
        assertTrue(list.isEmpty());
        list = attributeCollection.getFloatArrayList("key00");
        assertTrue(list.isEmpty());
        list = attributeCollection.getFloatArrayList("key1");
        assertEquals(123.0f, list.get(0), 0.0000001);
        assertEquals(321f, list.get(1), 0.0000001);
        list = attributeCollection.getFloatArrayList("key1001");
        assertEquals(123.0f, list.get(0), 0.0000001);
        assertEquals(321f, list.get(1), 0.0000001);
        list = attributeCollection.getFloatArrayList("key1002");
        assertEquals(123.0f, list.get(0), 0.0000001);
        assertEquals(321f, list.get(1), 0.0000001);
        list = attributeCollection.getFloatArrayList("key2");
        assertNull(list);
        list = attributeCollection.getFloatArrayList("key3");
        assertNull(list);
        list = attributeCollection.getFloatArrayList("key4");
        assertNull(list);
        list = attributeCollection.getFloatArrayList("key5");
        assertEquals(111.0f, list.get(0), 0.0000001);
        assertEquals(222f, list.get(1), 0.0000001);
        list = attributeCollection.getFloatArrayList("key6");
        assertNull(list);
        list = attributeCollection.getFloatArrayList("key7");
        assertNull(list);
        list = attributeCollection.getFloatArrayList("key8");
        assertNull(list);
        list = attributeCollection.getFloatArrayList("key88");
        assertNull(list);
        list = attributeCollection.getFloatArrayList("key9");
        assertNull(list);
        list = attributeCollection.getFloatArrayList("key10");
        assertEquals(123.0f, list.get(0), 0.0000001);
        assertEquals(321f, list.get(1), 0.0000001);
        ArrayList<Float> defaultValue = new ArrayList<>();
        defaultValue.add(111.0f);
        defaultValue.add(222.0f);
        defaultValue.add(333.0f);
        list = attributeCollection.getFloatArrayList("key8", defaultValue);
        assertEquals(111.0f, list.get(0), 0.0000001);
        assertEquals(222.0f, list.get(1), 0.0000001);
        assertEquals(333.0f, list.get(2), 0.0000001);
    }

    @Test
    public void baseDoubleListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_list key=\"key0\" type=\"double\" >\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key00\" type=\"double\" />\n"
                + "<attribute_list key=\"key1\" type=\"double\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1001\" type=\"double\" >\n"
                + "<sub value=\"123.0f\" >\n"
                + "</sub>"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1002\" type=\"double\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" >\n"
                + "</sub>"
                + "</attribute_list>"
                + "<attribute_list key1=\"key2\" type=\"double\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key3\" typ=\"double\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key4\" type=\"double\" append=\"append1\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"double\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key6\" type=\"double\" >\n"
                + "<su value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key7\" type=\"double\" >\n"
                + "<sub valu=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key8\" type=\"double\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key88\" type=\"double\" >\n"
                + "<sub value=\"\" />\n"
                + "<sub value=\"\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key9\" type=\"doubl\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key10\" type=\"Double\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"double\" >\n"
                + "<sub value=\"111.0f\" />\n"
                + "<sub value=\"222f\" />\n"
                + "</attribute_list>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayList<Double> list = attributeCollection.getDoubleArrayList("key0");
        assertTrue(list.isEmpty());
        list = attributeCollection.getDoubleArrayList("key00");
        assertTrue(list.isEmpty());
        list = attributeCollection.getDoubleArrayList("key1");
        assertEquals(123.0f, list.get(0), 0.0000001);
        assertEquals(321f, list.get(1), 0.0000001);
        list = attributeCollection.getDoubleArrayList("key1001");
        assertEquals(123.0f, list.get(0), 0.0000001);
        assertEquals(321f, list.get(1), 0.0000001);
        list = attributeCollection.getDoubleArrayList("key1002");
        assertEquals(123.0f, list.get(0), 0.0000001);
        assertEquals(321f, list.get(1), 0.0000001);
        list = attributeCollection.getDoubleArrayList("key2");
        assertNull(list);
        list = attributeCollection.getDoubleArrayList("key3");
        assertNull(list);
        list = attributeCollection.getDoubleArrayList("key4");
        assertNull(list);
        list = attributeCollection.getDoubleArrayList("key5");
        assertEquals(111.0f, list.get(0), 0.0000001);
        assertEquals(222f, list.get(1), 0.0000001);
        list = attributeCollection.getDoubleArrayList("key6");
        assertNull(list);
        list = attributeCollection.getDoubleArrayList("key7");
        assertNull(list);
        list = attributeCollection.getDoubleArrayList("key8");
        assertNull(list);
        list = attributeCollection.getDoubleArrayList("key88");
        assertNull(list);
        list = attributeCollection.getDoubleArrayList("key9");
        assertNull(list);
        list = attributeCollection.getDoubleArrayList("key10");
        assertEquals(123.0f, list.get(0), 0.0000001);
        assertEquals(321f, list.get(1), 0.0000001);
        ArrayList<Double> defaultValue = new ArrayList<>();
        defaultValue.add((double) 111.0f);
        defaultValue.add((double) 222.0f);
        defaultValue.add((double) 333.0f);
        list = attributeCollection.getDoubleArrayList("key8", defaultValue);
        assertEquals(111.0f, list.get(0), 0.0000001);
        assertEquals(222.0f, list.get(1), 0.0000001);
        assertEquals(333.0f, list.get(2), 0.0000001);
    }

    @Test
    public void baseCharListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_list key=\"key0\" type=\"char\" >\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key00\" type=\"char\" />\n"
                + "<attribute_list key=\"key1\" type=\"char\" >\n"
                + "<sub value=\"a\" />\n"
                + "<sub value=\"0\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1001\" type=\"char\" >\n"
                + "<sub value=\"a\" >\n"
                + "</sub>"
                + "<sub value=\"0\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1002\" type=\"char\" >\n"
                + "<sub value=\"a\" />\n"
                + "<sub value=\"0\" >\n"
                + "</sub>"
                + "</attribute_list>"
                + "<attribute_list key1=\"key2\" type=\"char\" >\n"
                + "<sub value=\"a\" />\n"
                + "<sub value=\"0\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key3\" typ=\"char\" >\n"
                + "<sub value=\"a\" />\n"
                + "<sub value=\"0\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key4\" type=\"char\" append=\"append1\" >\n"
                + "<sub value=\"a\" />\n"
                + "<sub value=\"0\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"char\" >\n"
                + "<sub value=\"a\" />\n"
                + "<sub value=\"0\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key6\" type=\"char\" >\n"
                + "<su value=\"a\" />\n"
                + "<sub value=\"0\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key7\" type=\"char\" >\n"
                + "<sub valu=\"a\" />\n"
                + "<sub value=\"0\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key8\" type=\"char\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key88\" type=\"char\" >\n"
                + "<sub value=\"\" />\n"
                + "<sub value=\"\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key9\" type=\"cha\" >\n"
                + "<sub value=\"a\" />\n"
                + "<sub value=\"0\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key10\" type=\"Char\" >\n"
                + "<sub value=\"a\" />\n"
                + "<sub value=\"0\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"char\" >\n"
                + "<sub value=\"b\" />\n"
                + "<sub value=\"2\" />\n"
                + "</attribute_list>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayList<Character> list = attributeCollection.getCharacterArrayList("key0");
        assertTrue(list.isEmpty());
        list = attributeCollection.getCharacterArrayList("key00");
        assertTrue(list.isEmpty());
        list = attributeCollection.getCharacterArrayList("key1");
        assertEquals('a', list.get(0).charValue());
        assertEquals('0', list.get(1).charValue());
        list = attributeCollection.getCharacterArrayList("key1001");
        assertEquals('a', list.get(0).charValue());
        assertEquals('0', list.get(1).charValue());
        list = attributeCollection.getCharacterArrayList("key1002");
        assertEquals('a', list.get(0).charValue());
        assertEquals('0', list.get(1).charValue());
        list = attributeCollection.getCharacterArrayList("key2");
        assertNull(list);
        list = attributeCollection.getCharacterArrayList("key3");
        assertNull(list);
        list = attributeCollection.getCharacterArrayList("key4");
        assertNull(list);
        list = attributeCollection.getCharacterArrayList("key5");
        assertEquals('b', list.get(0).charValue());
        assertEquals('2', list.get(1).charValue());
        list = attributeCollection.getCharacterArrayList("key6");
        assertNull(list);
        list = attributeCollection.getCharacterArrayList("key7");
        assertNull(list);
        list = attributeCollection.getCharacterArrayList("key8");
        assertNull(list);
        list = attributeCollection.getCharacterArrayList("key8");
        assertNull(list);
        list = attributeCollection.getCharacterArrayList("key10");
        assertEquals('a', list.get(0).charValue());
        assertEquals('0', list.get(1).charValue());
        ArrayList<Character> defaultValue = new ArrayList<>();
        defaultValue.add('a');
        defaultValue.add('z');
        defaultValue.add('\0');
        list = attributeCollection.getCharacterArrayList("key8", defaultValue);
        assertEquals('a', list.get(0).charValue());
        assertEquals('z', list.get(1).charValue());
        assertEquals('\0', list.get(2).charValue());
    }

    @Test
    public void baseStringListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_list key=\"key0\" type=\"string\" >\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key00\" type=\"string\" />\n"
                + "<attribute_list key=\"key1\" type=\"string\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1001\" type=\"string\" >\n"
                + "<sub value=\"hello\" >\n"
                + "</sub>"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1002\" type=\"string\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" >\n"
                + "</sub>"
                + "</attribute_list>"
                + "<attribute_list key1=\"key2\" type=\"string\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key3\" typ=\"string\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key4\" type=\"string\" append=\"append1\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"string\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key6\" type=\"string\" >\n"
                + "<su value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key7\" type=\"string\" >\n"
                + "<sub valu=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key8\" type=\"string\" >\n"
                + "<sub value=\"\" />\n"
                + "<sub value=\"\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key9\" type=\"strin\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"wold\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key10\" type=\"String\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"string\" >\n"
                + "<sub value=\"bi\" />\n"
                + "<sub value=\"li\" />\n"
                + "</attribute_list>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        ArrayList<String> list = attributeCollection.getStringArrayList("key0");
        assertTrue(list.isEmpty());
        list = attributeCollection.getStringArrayList("key00");
        assertTrue(list.isEmpty());
        list = attributeCollection.getStringArrayList("key1");
        assertEquals("hello", list.get(0));
        assertEquals("world", list.get(1));
        list = attributeCollection.getStringArrayList("key1001");
        assertEquals("hello", list.get(0));
        assertEquals("world", list.get(1));
        list = attributeCollection.getStringArrayList("key1002");
        assertEquals("hello", list.get(0));
        assertEquals("world", list.get(1));
        list = attributeCollection.getStringArrayList("key2");
        assertNull(list);
        list = attributeCollection.getStringArrayList("key3");
        assertNull(list);
        list = attributeCollection.getStringArrayList("key4");
        assertNull(list);
        list = attributeCollection.getStringArrayList("key5");
        assertEquals("bi", list.get(0));
        assertEquals("li", list.get(1));
        list = attributeCollection.getStringArrayList("key6");
        assertNull(list);
        list = attributeCollection.getStringArrayList("key7");
        assertNull(list);
        list = attributeCollection.getStringArrayList("key8");
        assertEquals("", list.get(0));
        assertEquals("", list.get(1));
        list = attributeCollection.getStringArrayList("key10");
        assertEquals("hello", list.get(0));
        assertEquals("world", list.get(1));
        ArrayList<String> defaultValue = new ArrayList<>();
        defaultValue.add("when");
        defaultValue.add("I am");
        defaultValue.add("a child");
        list = attributeCollection.getStringArrayList("key11", defaultValue);
        assertEquals("when", list.get(0));
        assertEquals("I am", list.get(1));
        assertEquals("a child", list.get(2));
    }

    @Test
    public void customListAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute_list key=\"key0\" type=\"PersonAttribute\" >\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key00\" type=\"PersonAttribute\" />\n"
                + "<attribute_list key=\"key1\" type=\"PersonAttribute\" >\n"
                + "<sub name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1001\" type=\"PersonAttribute\" >\n"
                + "<sub name=\"li\" age=\"20\" high=\"180\" weight=\"70\" >\n"
                + "</sub>"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key1002\" type=\"PersonAttribute\" >\n"
                + "<sub name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" >\n"
                + "</sub>"
                + "</attribute_list>"
                + "<attribute_list key1=\"key2\" type=\"PersonAttribute\" >\n"
                + "<sub name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key3\" typ=\"PersonAttribute\" >\n"
                + "<sub name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key4\" type=\"PersonAttribute\" append=\"append1\" >\n"
                + "<sub name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"PersonAttribute\" >\n"
                + "<sub name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key6\" type=\"PersonAttribute\" >\n"
                + "<su name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key7\" type=\"PersonAttribute\" >\n"
                + "<sub name=\"li\" ag=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key8\" type=\"PersonAttribute\" >\n"
                + "<sub name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key9\" type=\"PersonConfi\" >\n"
                + "<sub name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key10\" type=\"PersonAttribute\" >\n"
                + "<sub name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key11\" type=\"PersonAttribute\" >\n"
                + "<sub name=\"li\" age=\"\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_list key=\"key5\" type=\"PersonAttribute\" >\n"
                + "<sub name=\"zhou\" age=\"40\" high=\"200\" weight=\"100\" />\n"
                + "<sub name=\"wu\" age=\"30\" high=\"190\" weight=\"120\" />\n"
                + "</attribute_list>"
                + ATTRIBUTE_END;

        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        PersonAttribute attribute;
        ArrayList<PersonAttribute> list = attributeCollection.getAttributeArrayList("key0");
        assertTrue(list.isEmpty());
        list = attributeCollection.getAttributeArrayList("key00");
        assertTrue(list.isEmpty());
        list = attributeCollection.getAttributeArrayList("key1");
        attribute = list.get(0);
        assertEquals("li", attribute.getName());
        assertEquals(20, attribute.getAge());
        assertEquals(180, attribute.getHigh());
        assertEquals(70, attribute.getWeight());
        attribute = list.get(1);
        assertEquals("wang", attribute.getName());
        assertEquals(21, attribute.getAge());
        assertEquals(185, attribute.getHigh());
        assertEquals(80, attribute.getWeight());
        list = attributeCollection.getAttributeArrayList("key1001");
        attribute = list.get(0);
        assertEquals("li", attribute.getName());
        assertEquals(20, attribute.getAge());
        assertEquals(180, attribute.getHigh());
        assertEquals(70, attribute.getWeight());
        attribute = list.get(1);
        assertEquals("wang", attribute.getName());
        assertEquals(21, attribute.getAge());
        assertEquals(185, attribute.getHigh());
        assertEquals(80, attribute.getWeight());
        list = attributeCollection.getAttributeArrayList("key1002");
        attribute = list.get(0);
        assertEquals("li", attribute.getName());
        assertEquals(20, attribute.getAge());
        assertEquals(180, attribute.getHigh());
        assertEquals(70, attribute.getWeight());
        attribute = list.get(1);
        assertEquals("wang", attribute.getName());
        assertEquals(21, attribute.getAge());
        assertEquals(185, attribute.getHigh());
        assertEquals(80, attribute.getWeight());
        list = attributeCollection.getAttributeArrayList("key2");
        assertNull(list);
        list = attributeCollection.getAttributeArrayList("key3");
        assertNull(list);
        list = attributeCollection.getAttributeArrayList("key4");
        assertNull(list);
        list = attributeCollection.getAttributeArrayList("key5");
        attribute = list.get(0);
        assertEquals("zhou", attribute.getName());
        assertEquals(40, attribute.getAge());
        assertEquals(200, attribute.getHigh());
        assertEquals(100, attribute.getWeight());
        attribute = list.get(1);
        assertEquals("wu", attribute.getName());
        assertEquals(30, attribute.getAge());
        assertEquals(190, attribute.getHigh());
        assertEquals(120, attribute.getWeight());
        list = attributeCollection.getAttributeArrayList("key6");
        assertNull(list);
        list = attributeCollection.getAttributeArrayList("key7");
        attribute = list.get(0);
        assertEquals("li", attribute.getName());
        assertEquals(0, attribute.getAge());
        assertEquals(180, attribute.getHigh());
        assertEquals(70, attribute.getWeight());
        attribute = list.get(1);
        assertEquals("wang", attribute.getName());
        assertEquals(21, attribute.getAge());
        assertEquals(185, attribute.getHigh());
        assertEquals(80, attribute.getWeight());
        list = attributeCollection.getAttributeArrayList("key8");
        attribute = list.get(0);
        assertEquals("li", attribute.getName());
        assertEquals(20, attribute.getAge());
        assertEquals(180, attribute.getHigh());
        assertEquals(70, attribute.getWeight());
        attribute = list.get(1);
        assertEquals("wang", attribute.getName());
        assertEquals(21, attribute.getAge());
        assertEquals(185, attribute.getHigh());
        assertEquals(80, attribute.getWeight());
        list = attributeCollection.getAttributeArrayList("key10");
        attribute = list.get(0);
        assertEquals("li", attribute.getName());
        assertEquals(20, attribute.getAge());
        assertEquals(180, attribute.getHigh());
        assertEquals(70, attribute.getWeight());
        attribute = list.get(1);
        assertEquals("wang", attribute.getName());
        assertEquals(21, attribute.getAge());
        assertEquals(185, attribute.getHigh());
        assertEquals(80, attribute.getWeight());
        list = attributeCollection.getAttributeArrayList("key11");
        attribute = list.get(0);
        assertEquals("li", attribute.getName());
        assertEquals(0, attribute.getAge());
        assertEquals(180, attribute.getHigh());
        assertEquals(70, attribute.getWeight());
        attribute = list.get(1);
        assertEquals("wang", attribute.getName());
        assertEquals(21, attribute.getAge());
        assertEquals(185, attribute.getHigh());
        assertEquals(80, attribute.getWeight());
        ArrayList<PersonAttribute> defaultValue = new ArrayList<>();
        defaultValue.add(new PersonAttribute("zhao", 10, 160, 110, false));
        defaultValue.add(new PersonAttribute("qian", 20, 170, 130, true));
        defaultValue.add(new PersonAttribute("sun", 30, 180, 150, false));
        list = attributeCollection.getAttributeArrayList("key12", defaultValue);
        attribute = list.get(0);
        assertEquals("zhao", attribute.getName());
        assertEquals(10, attribute.getAge());
        assertEquals(160, attribute.getHigh());
        assertEquals(110, attribute.getWeight());
        assertFalse(attribute.getGender());
        attribute = list.get(1);
        assertEquals("qian", attribute.getName());
        assertEquals(20, attribute.getAge());
        assertEquals(170, attribute.getHigh());
        assertEquals(130, attribute.getWeight());
        assertTrue(attribute.getGender());
        attribute = list.get(2);
        assertEquals("sun", attribute.getName());
        assertEquals(30, attribute.getAge());
        assertEquals(180, attribute.getHigh());
        assertEquals(150, attribute.getWeight());
        assertFalse(attribute.getGender());
    }

}