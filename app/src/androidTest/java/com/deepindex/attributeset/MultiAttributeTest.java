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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class MultiAttributeTest {
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
    public void multiAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute key=\"key0\" type=\"boolean\" value=\"true\">\n"
                + "</attribute>"
                + "<attribute_map key=\"key29\" type=\"string\" >\n"
                + "<sub key=\"subkey1\" value=\"hello\" />\n"
                + "<sub key=\"subkey2\" value=\"world\" />\n"
                + "</attribute_map>"
                + "<attribute_list key=\"key11\" type=\"byte\" >\n"
                + "<sub value=\"12\" />\n"
                + "<sub value=\"32\" />\n"
                + "</attribute_list>"
                + "<attribute key=\"key1\" type=\"byte\" value=\"123\">\n"
                + "</attribute>"
                + "<attribute_map key=\"key23\" type=\"short\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute_list key=\"key12\" type=\"short\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute key=\"key2\" type=\"short\" value=\"1234\">\n"
                + "</attribute>"
                + "<attribute key=\"key3\" type=\"int\" value=\"12345\">\n"
                + "</attribute>"
                + "<attribute_list key=\"key15\" type=\"float\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute key=\"key4\" type=\"long\" value=\"123456\">\n"
                + "</attribute>"
                + "<attribute_list key=\"key13\" type=\"int\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute key=\"key5\" type=\"float\" value=\"123.0f\">\n"
                + "</attribute>"
                + "<attribute_list key=\"key14\" type=\"long\" >\n"
                + "<sub value=\"123\" />\n"
                + "<sub value=\"321\" />\n"
                + "</attribute_list>"
                + "<attribute key=\"key6\" type=\"double\" value=\"123.0f\">\n"
                + "</attribute>"
                + "<attribute_map key=\"key22\" type=\"byte\" >\n"
                + "<sub key=\"subkey1\" value=\"12\" />\n"
                + "<sub key=\"subkey2\" value=\"32\" />\n"
                + "</attribute_map>"
                + "<attribute_list key=\"key17\" type=\"char\" >\n"
                + "<sub value=\"a\" />\n"
                + "<sub value=\"0\" />\n"
                + "</attribute_list>"
                + "<attribute_map key=\"key26\" type=\"float\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + "<attribute_map key=\"key25\" type=\"long\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute key=\"key7\" type=\"char\" value=\"a\">\n"
                + "</attribute>"
                + "<attribute_map key=\"key28\" type=\"char\" >\n"
                + "<sub key=\"subkey1\" value=\"a\" />\n"
                + "<sub key=\"subkey2\" value=\"0\" />\n"
                + "</attribute_map>"
                + "<attribute_list key=\"key16\" type=\"double\" >\n"
                + "<sub value=\"123.0f\" />\n"
                + "<sub value=\"321f\" />\n"
                + "</attribute_list>"
                + "<attribute_map key=\"key21\" type=\"boolean\" >\n"
                + "<sub key=\"subkey1\" value=\"true\" />\n"
                + "<sub key=\"subkey2\" value=\"false\" />\n"
                + "</attribute_map>"
                + "<attribute key=\"key8\" type=\"string\" value=\"value1\">\n"
                + "</attribute>"
                + "<attribute_list key=\"key18\" type=\"string\" >\n"
                + "<sub value=\"hello\" />\n"
                + "<sub value=\"world\" />\n"
                + "</attribute_list>"
                + "<attribute_map key=\"key24\" type=\"int\" >\n"
                + "<sub key=\"subkey1\" value=\"123\" />\n"
                + "<sub key=\"subkey2\" value=\"321\" />\n"
                + "</attribute_map>"
                + "<attribute key=\"key9\" type=\"PersonAttribute\" name=\"li\" age=\"20\""
                + " high=\"180\" weight=\"150\" />\n"
                + "<attribute_list key=\"key10\" type=\"boolean\" >\n"
                + "<sub value=\"true\" />\n"
                + "<sub value=\"false\" />\n"
                + "</attribute_list>"
                + "<attribute_map key=\"key30\" type=\"PersonAttribute\" >\n"
                + "<sub key=\"subkey1\" name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub key=\"subkey2\" name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_map>"
                + "<attribute_list key=\"key19\" type=\"PersonAttribute\" >\n"
                + "<sub name=\"li\" age=\"20\" high=\"180\" weight=\"70\" />\n"
                + "<sub name=\"wang\" age=\"21\" high=\"185\" weight=\"80\" />\n"
                + "</attribute_list>"
                + "<attribute_map key=\"key27\" type=\"double\" >\n"
                + "<sub key=\"subkey1\" value=\"123.0f\" />\n"
                + "<sub key=\"subkey2\" value=\"321f\" />\n"
                + "</attribute_map>"
                + ATTRIBUTE_END;
        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        assertTrue(attributeCollection.getBoolean("key0"));
        assertEquals(123, attributeCollection.getByte("key1"));
        assertEquals(1234, attributeCollection.getShort("key2"));
        assertEquals(12345, attributeCollection.getInt("key3"));
        assertEquals(123456, attributeCollection.getLong("key4"));
        assertEquals(123f, attributeCollection.getFloat("key5"), 0.0000001f);
        assertEquals(123f, attributeCollection.getDouble("key6"), 0.0000001f);
        assertEquals('a', attributeCollection.getChar("key7"));
        assertEquals("value1", attributeCollection.getString("key8"));
        PersonAttribute result = attributeCollection.getAttribute("key9");
        assertEquals("li", result.getName());
        assertEquals(20, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        ArrayList<Boolean> list = attributeCollection.getBooleanArrayList("key10");
        assertTrue(list.get(0));
        assertFalse(list.get(1));
        ArrayList<Byte> list1 = attributeCollection.getByteArrayList("key11");
        assertEquals(12, list1.get(0).byteValue());
        assertEquals(32, list1.get(1).byteValue());
        ArrayList<Short> list2 = attributeCollection.getShortArrayList("key12");
        assertEquals(123, list2.get(0).shortValue());
        assertEquals(321, list2.get(1).shortValue());
        ArrayList<Integer> list3 = attributeCollection.getIntegerArrayList("key13");
        assertEquals(123, list3.get(0).intValue());
        assertEquals(321, list3.get(1).intValue());
        ArrayList<Long> list4 = attributeCollection.getLongArrayList("key14");
        assertEquals(123L, list4.get(0).longValue());
        assertEquals(321L, list4.get(1).longValue());
        ArrayList<Float> list5 = attributeCollection.getFloatArrayList("key15");
        assertEquals(123.0f, list5.get(0), 0.0000001);
        assertEquals(321f, list5.get(1), 0.0000001);
        ArrayList<Double> list6 = attributeCollection.getDoubleArrayList("key16");
        assertEquals(123.0f, list6.get(0), 0.0000001);
        assertEquals(321f, list6.get(1), 0.0000001);
        ArrayList<Character> list7 = attributeCollection.getCharacterArrayList("key17");
        assertEquals('a', list7.get(0).charValue());
        assertEquals('0', list7.get(1).charValue());
        ArrayList<String> list8 = attributeCollection.getStringArrayList("key18");
        assertEquals("hello", list8.get(0));
        assertEquals("world", list8.get(1));
        ArrayList<PersonAttribute> list9 = attributeCollection.getAttributeArrayList("key19");
        PersonAttribute attribute = list9.get(0);
        assertEquals("li", attribute.getName());
        assertEquals(20, attribute.getAge());
        assertEquals(180, attribute.getHigh());
        assertEquals(70, attribute.getWeight());
        attribute = list9.get(1);
        assertEquals("wang", attribute.getName());
        assertEquals(21, attribute.getAge());
        assertEquals(185, attribute.getHigh());
        assertEquals(80, attribute.getWeight());

        ArrayMap<String, Boolean> map1 = attributeCollection.getBooleanArrayMap("key21");
        Boolean value = map1.get("subkey1");
        assertNotNull(value);
        assertTrue(value);
        value = map1.get("subkey2");
        assertNotNull(value);
        assertFalse(value);
        ArrayMap<String, Byte> map2 = attributeCollection.getByteArrayMap("key22");
        Byte value2 = map2.get("subkey1");
        assertNotNull(value2);
        assertEquals(12, value2.byteValue());
        value2 = map2.get("subkey2");
        assertNotNull(value2);
        assertEquals(32, value2.byteValue());
        ArrayMap<String, Short> map3 = attributeCollection.getShortArrayMap("key23");
        Short value3 = map3.get("subkey1");
        assertNotNull(value3);
        assertEquals(123, value3.shortValue());
        value3 = map3.get("subkey2");
        assertNotNull(value3);
        assertEquals(321, value3.shortValue());

        ArrayMap<String, Integer> map4 = attributeCollection.getIntegerArrayMap("key24");
        Integer value4 = map4.get("subkey1");
        assertNotNull(value4);
        assertEquals(123, value4.shortValue());
        value4 = map4.get("subkey2");
        assertNotNull(value4);
        assertEquals(321, value4.shortValue());
        ArrayMap<String, Long> map5 = attributeCollection.getLongArrayMap("key25");
        Long value5 = map5.get("subkey1");
        assertNotNull(value5);
        assertEquals(123, value5.shortValue());
        value5 = map5.get("subkey2");
        assertNotNull(value5);
        assertEquals(321, value5.shortValue());

        ArrayMap<String, Float> map6 = attributeCollection.getFloatArrayMap("key26");
        Float value6 = map6.get("subkey1");
        assertNotNull(value6);
        assertEquals(123.0f, value6, 0.0000001);
        value6 = map6.get("subkey2");
        assertNotNull(value6);
        assertEquals(321f, value6, 0.0000001);
        ArrayMap<String, Double> map7 = attributeCollection.getDoubleArrayMap("key27");
        Double value7 = map7.get("subkey1");
        assertNotNull(value7);
        assertEquals(123.0f, value7, 0.0000001);
        value7 = map7.get("subkey2");
        assertNotNull(value7);
        assertEquals(321f, value7, 0.0000001);
        ArrayMap<String, Character> map8 = attributeCollection.getCharacterArrayMap("key28");
        Character value8 = map8.get("subkey1");
        assertNotNull(value8);
        assertEquals('a', value8.charValue());
        value8 = map8.get("subkey2");
        assertNotNull(value8);
        assertEquals('0', value8.charValue());
        ArrayMap<String, String> map9 = attributeCollection.getStringArrayMap("key29");
        String value9 = map9.get("subkey1");
        assertNotNull(value9);
        assertEquals("hello", value9);
        value9 = map9.get("subkey2");
        assertNotNull(value9);
        assertEquals("world", value9);

        ArrayMap<String, PersonAttribute> map10 = attributeCollection.getAttributeArrayMap("key30");
        PersonAttribute value10 = map10.get("subkey1");
        assertNotNull(value10);
        assertEquals("li", value10.getName());
        assertEquals(20, value10.getAge());
        assertEquals(180, value10.getHigh());
        assertEquals(70, value10.getWeight());
        value10 = map10.get("subkey2");
        assertNotNull(value10);
        assertEquals("wang", value10.getName());
        assertEquals(21, value10.getAge());
        assertEquals(185, value10.getHigh());
        assertEquals(80, value10.getWeight());
    }

    @Test
    public void customNestedObjectAttribute() {
        String fileInfo = XML_START
                + ATTRIBUTE_BEGIN
                + "<attribute key=\"key1\" type=\"PersonAttribute\" name=\"li\" age=\"11\" high=\"111\" weight=\"111\" >\n"
                + "    <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wang\" age=\"12\" high=\"112\" weight=\"112\" />\n"
                + "</attribute>\n"
                + "<attribute key=\"key101\" type=\"PersonAttribute\" name=\"li\" age=\"11\" high=\"111\" weight=\"111\" >\n"
                + "    <attribute function_name=\"setAge\" type=\"int\" value=\"12\" />\n"

                + "</attribute>\n"
                + "<attribute key=\"key2\" type=\"PersonAttribute\" name=\"zhao\" age=\"22\" high=\"222\" weight=\"222\" >\n"
                + "    <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"qian\" age=\"23\" high=\"223\" weight=\"223\" >\n"
                + "    </attribute>\n"
                + "</attribute>\n"
                + "<attribute key=\"key3\" type=\"PersonAttribute\" name=\"sun\" age=\"33\" high=\"333\" weight=\"333\" >\n"
                + "    <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"zhou\" age=\"34\" high=\"334\" weight=\"334\" >\n"
                + "        <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wu\" age=\"35\" high=\"335\" weight=\"335\" />\n"
                + "    </attribute>\n"
                + "</attribute>\n"
                + "<attribute key=\"key4\" type=\"PersonAttribute\" name=\"sun\" age=\"33\" high=\"333\" weight=\"333\" >\n"
                + "    <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"zhou\" age=\"34\" high=\"334\" weight=\"334\" >\n"
                + "        <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wu\" age=\"35\" high=\"335\" weight=\"335\" >\n"
                + "        </attribute>\n"
                + "    </attribute>\n"
                + "</attribute>\n"
                + "<attribute key=\"key11\" type=\"PersonAttribute\" name=\"wang\" age=\"11\" high=\"111\" weight=\"111\" >\n"
                + "    <attribute_list function_name=\"setColleagues\" type=\"PersonAttribute\" >\n"
                + "        <sub name=\"li\" age=\"12\" high=\"122\" weight=\"112\" />\n"
                + "        <sub name=\"yang\" age=\"13\" high=\"133\" weight=\"113\" />\n"
                + "    </attribute_list>\n"
                + "</attribute>\n"
                + "<attribute key=\"key12\" type=\"PersonAttribute\" name=\"wang\" age=\"11\" high=\"111\" weight=\"111\" >\n"
                + "    <attribute_list function_name=\"setColleagues\" type=\"PersonAttribute\" >\n"
                + "        <sub name=\"li\" age=\"12\" high=\"122\" weight=\"112\" >\n"
                + "        </sub>"
                + "        <sub name=\"yang\" age=\"13\" high=\"133\" weight=\"113\" />\n"
                + "    </attribute_list>\n"
                + "</attribute>\n"
                + "<attribute key=\"key13\" type=\"PersonAttribute\" name=\"wang\" age=\"11\" high=\"111\" weight=\"111\" >\n"
                + "    <attribute_list function_name=\"setColleagues\" type=\"PersonAttribute\" >\n"
                + "        <sub name=\"li\" age=\"12\" high=\"122\" weight=\"112\" >\n"
                + "            <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wu\" age=\"35\" high=\"335\" weight=\"335\" >\n"
                + "            </attribute>\n"
                + "        </sub>"
                + "        <sub name=\"yang\" age=\"13\" high=\"133\" weight=\"113\" />\n"
                + "    </attribute_list>\n"
                + "</attribute>\n"
                + "<attribute key=\"key14\" type=\"PersonAttribute\" name=\"wang\" age=\"11\" high=\"111\" weight=\"111\" >\n"
                + "    <attribute_list function_name=\"setColleagues\" type=\"PersonAttribute\" >\n"
                + "        <sub name=\"li\" age=\"12\" high=\"122\" weight=\"112\" >\n"
                + "        </sub>"
                + "        <sub name=\"yang\" age=\"13\" high=\"133\" weight=\"113\" >\n"
                + "            <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wu\" age=\"35\" high=\"335\" weight=\"335\" >\n"
                + "            </attribute>\n"
                + "        </sub>"
                + "    </attribute_list>\n"
                + "</attribute>\n"
                + "<attribute key=\"key15\" type=\"PersonAttribute\" name=\"wang\" age=\"11\" high=\"111\" weight=\"111\" >\n"
                + "    <attribute_list function_name=\"setColleagues\" type=\"PersonAttribute\" >\n"
                + "        <sub name=\"li\" age=\"12\" high=\"122\" weight=\"112\" >\n"
                + "            <attribute_list function_name=\"setColleagues\" type=\"PersonAttribute\" >\n"
                + "                <sub name=\"li\" age=\"12\" high=\"122\" weight=\"112\" >\n"
                + "                </sub>"
                + "                <sub name=\"yang\" age=\"13\" high=\"133\" weight=\"113\" />\n"
                + "            </attribute_list>\n"
                + "        </sub>"
                + "        <sub name=\"yang\" age=\"13\" high=\"133\" weight=\"113\" >\n"
                + "        </sub>"
                + "    </attribute_list>\n"
                + "</attribute>\n"
                + "<attribute key=\"key16\" type=\"PersonAttribute\" name=\"wang\" age=\"11\" high=\"111\" weight=\"111\" >\n"
                + "    <attribute_list function_name=\"setColleagues\" type=\"PersonAttribute\" >\n"
                + "        <sub name=\"li\" age=\"12\" high=\"122\" weight=\"112\" >\n"
                + "        </sub>"
                + "        <sub name=\"yang\" age=\"13\" high=\"133\" weight=\"113\" >\n"
                + "            <attribute_list function_name=\"setColleagues\" type=\"PersonAttribute\" >\n"
                + "                <sub name=\"li\" age=\"12\" high=\"122\" weight=\"112\" >\n"
                + "                </sub>"
                + "                <sub name=\"yang\" age=\"13\" high=\"133\" weight=\"113\" />\n"
                + "            </attribute_list>\n"
                + "        </sub>"
                + "    </attribute_list>\n"
                + "</attribute>\n"
                + "<attribute key=\"key20\" type=\"PersonAttribute\" name=\"li\" age=\"20\" high=\"180\" weight=\"150\" >\n"
                + "    <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
                + "        <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" />\n"
                + "        <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" />\n"
                + "        <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" />\n"
                + "    </attribute_map>\n"
                + "</attribute>"
                + "<attribute key=\"key21\" type=\"PersonAttribute\" name=\"li\" age=\"20\" high=\"180\" weight=\"150\" >\n"
                + "    <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
                + "        <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" >\n"
                + "            <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"wu\" age=\"35\" high=\"335\" weight=\"335\" >\n"
                + "            </attribute>\n"
                + "        </sub>"
                + "        <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" />\n"
                + "        <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "            <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"li\" age=\"36\" high=\"335\" weight=\"335\" >\n"
                + "            </attribute>\n"
                + "        </sub>"
                + "    </attribute_map>\n"
                + "</attribute>"
                + "<attribute key=\"key22\" type=\"PersonAttribute\" name=\"li\" age=\"20\" high=\"180\" weight=\"150\" >\n"
                + "    <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
                + "        <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" />\n"
                + "        <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "            <attribute function_name=\"setBestFriend\" type=\"PersonAttribute\" name=\"li\" age=\"36\" high=\"335\" weight=\"335\" >\n"
                + "            </attribute>\n"
                + "        </sub>"
                + "        <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" />\n"
                + "    </attribute_map>\n"
                + "</attribute>"
                + "<attribute key=\"key23\" type=\"PersonAttribute\" name=\"li\" age=\"20\" high=\"180\" weight=\"150\" >\n"
                + "    <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
                + "        <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" >\n"
                + "            <attribute_list function_name=\"setColleagues\" type=\"PersonAttribute\" >\n"
                + "                <sub name=\"li\" age=\"12\" high=\"122\" weight=\"112\" >\n"
                + "                </sub>"
                + "                <sub name=\"yang\" age=\"13\" high=\"133\" weight=\"113\" />\n"
                + "            </attribute_list>\n"
                + "        </sub>"
                + "        <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "        </sub>"
                + "        <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "            <attribute_list function_name=\"setColleagues\" type=\"PersonAttribute\" >\n"
                + "                <sub name=\"wang\" age=\"12\" high=\"122\" weight=\"112\" >\n"
                + "                </sub>"
                + "                <sub name=\"yang\" age=\"13\" high=\"133\" weight=\"113\" />\n"
                + "            </attribute_list>\n"
                + "        </sub>"
                + "    </attribute_map>\n"
                + "</attribute>"
                + "<attribute key=\"key24\" type=\"PersonAttribute\" name=\"li\" age=\"20\" high=\"180\" weight=\"150\" >\n"
                + "    <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
                + "        <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" >\n"
                + "        </sub>"
                + "        <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "            <attribute_list function_name=\"setColleagues\" type=\"PersonAttribute\" >\n"
                + "                <sub name=\"li\" age=\"12\" high=\"122\" weight=\"112\" >\n"
                + "                </sub>"
                + "                <sub name=\"yang\" age=\"13\" high=\"133\" weight=\"113\" />\n"
                + "            </attribute_list>\n"
                + "        </sub>"
                + "        <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "        </sub>"
                + "    </attribute_map>\n"
                + "</attribute>"
                + "<attribute key=\"key25\" type=\"PersonAttribute\" name=\"li\" age=\"20\" high=\"180\" weight=\"150\" >\n"
                + "    <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
                + "        <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" >\n"
                + "            <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
                + "                <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" >\n"
                + "                </sub>"
                + "                <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" />\n"
                + "                <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "                </sub>"
                + "             </attribute_map>\n"
                + "        </sub>"
                + "        <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "            <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
                + "                <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" >\n"
                + "                </sub>"
                + "                <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" />\n"
                + "                <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "                </sub>"
                + "             </attribute_map>\n"
                + "        </sub>"
                + "        <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "        </sub>"
                + "    </attribute_map>\n"
                + "</attribute>"
                + "<attribute key=\"key26\" type=\"PersonAttribute\" name=\"li\" age=\"20\" high=\"180\" weight=\"150\" >\n"
                + "    <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
                + "        <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" >\n"
                + "        </sub>"
                + "        <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "        </sub>"
                + "        <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "            <attribute_map function_name=\"setFriends\" type=\"PersonAttribute\" >\n"
                + "                <sub key=\"subkey1\" name=\"zhang\" age=\"22\" high=\"175\" weight=\"160\" >\n"
                + "                </sub>"
                + "                <sub key=\"subkey2\" name=\"qian\" age=\"21\" high=\"160\" weight=\"120\" />\n"
                + "                <sub key=\"subkey3\" name=\"zhou\" age=\"21\" high=\"160\" weight=\"120\" >\n"
                + "                </sub>"
                + "             </attribute_map>\n"
                + "        </sub>"
                + "    </attribute_map>\n"
                + "</attribute>"
                + ATTRIBUTE_END;
        //自动生成测试用例字符串，进行测试，多循环测试
        //function_name函数中也可能出现setFriendAge(List<Integer> age), setFriendAgeMap(Map<String, Integer> map);
        AttributeCollection attributeCollection = parseAttribute(fileInfo);
        PersonAttribute result = attributeCollection.getAttribute("key1");
        assertEquals("li", result.getName());
        assertEquals(11, result.getAge());
        assertEquals(111, result.getHigh());
        assertEquals(111, result.getWeight());
        PersonAttribute bestFriend = result.getBestFriend();
        assertEquals("wang", bestFriend.getName());
        assertEquals(12, bestFriend.getAge());
        assertEquals(112, bestFriend.getHigh());
        assertEquals(112, bestFriend.getWeight());

        result = attributeCollection.getAttribute("key101");
        assertEquals("li", result.getName());
        assertEquals(12, result.getAge());
        assertEquals(111, result.getHigh());
        assertEquals(111, result.getWeight());

        result = attributeCollection.getAttribute("key2");
        assertEquals("zhao", result.getName());
        assertEquals(22, result.getAge());
        assertEquals(222, result.getHigh());
        assertEquals(222, result.getWeight());
        bestFriend = result.getBestFriend();
        assertEquals("qian", bestFriend.getName());
        assertEquals(23, bestFriend.getAge());
        assertEquals(223, bestFriend.getHigh());
        assertEquals(223, bestFriend.getWeight());
        result = attributeCollection.getAttribute("key3");
        assertEquals("sun", result.getName());
        assertEquals(33, result.getAge());
        assertEquals(333, result.getHigh());
        assertEquals(333, result.getWeight());
        bestFriend = result.getBestFriend();
        assertEquals("zhou", bestFriend.getName());
        assertEquals(34, bestFriend.getAge());
        assertEquals(334, bestFriend.getHigh());
        assertEquals(334, bestFriend.getWeight());
        bestFriend = bestFriend.getBestFriend();
        assertEquals("wu", bestFriend.getName());
        assertEquals(35, bestFriend.getAge());
        assertEquals(335, bestFriend.getHigh());
        assertEquals(335, bestFriend.getWeight());
        result = attributeCollection.getAttribute("key4");
        assertEquals("sun", result.getName());
        assertEquals(33, result.getAge());
        assertEquals(333, result.getHigh());
        assertEquals(333, result.getWeight());
        bestFriend = result.getBestFriend();
        assertEquals("zhou", bestFriend.getName());
        assertEquals(34, bestFriend.getAge());
        assertEquals(334, bestFriend.getHigh());
        assertEquals(334, bestFriend.getWeight());
        bestFriend = bestFriend.getBestFriend();
        assertEquals("wu", bestFriend.getName());
        assertEquals(35, bestFriend.getAge());
        assertEquals(335, bestFriend.getHigh());
        assertEquals(335, bestFriend.getWeight());

        result = attributeCollection.getAttribute("key11");
        assertEquals("wang", result.getName());
        assertEquals(11, result.getAge());
        assertEquals(111, result.getHigh());
        assertEquals(111, result.getWeight());
        List<PersonAttribute> colleagues = result.getColleagues();
        PersonAttribute colleague = colleagues.get(0);
        assertEquals("li", colleague.getName());
        assertEquals(12, colleague.getAge());
        assertEquals(122, colleague.getHigh());
        assertEquals(112, colleague.getWeight());
        colleague = colleagues.get(1);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());

        result = attributeCollection.getAttribute("key12");
        assertEquals("wang", result.getName());
        assertEquals(11, result.getAge());
        assertEquals(111, result.getHigh());
        assertEquals(111, result.getWeight());
        colleagues = result.getColleagues();
        colleague = colleagues.get(0);
        assertEquals("li", colleague.getName());
        assertEquals(12, colleague.getAge());
        assertEquals(122, colleague.getHigh());
        assertEquals(112, colleague.getWeight());
        colleague = colleagues.get(1);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());

        result = attributeCollection.getAttribute("key13");
        assertEquals("wang", result.getName());
        assertEquals(11, result.getAge());
        assertEquals(111, result.getHigh());
        assertEquals(111, result.getWeight());
        colleagues = result.getColleagues();
        colleague = colleagues.get(0);
        assertEquals("li", colleague.getName());
        assertEquals(12, colleague.getAge());
        assertEquals(122, colleague.getHigh());
        assertEquals(112, colleague.getWeight());
        bestFriend = colleague.getBestFriend();
        assertEquals("wu", bestFriend.getName());
        assertEquals(35, bestFriend.getAge());
        assertEquals(335, bestFriend.getHigh());
        assertEquals(335, bestFriend.getWeight());
        colleague = colleagues.get(1);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());

        result = attributeCollection.getAttribute("key14");
        assertEquals("wang", result.getName());
        assertEquals(11, result.getAge());
        assertEquals(111, result.getHigh());
        assertEquals(111, result.getWeight());
        colleagues = result.getColleagues();
        colleague = colleagues.get(0);
        assertEquals("li", colleague.getName());
        assertEquals(12, colleague.getAge());
        assertEquals(122, colleague.getHigh());
        assertEquals(112, colleague.getWeight());
        colleague = colleagues.get(1);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());
        bestFriend = colleague.getBestFriend();
        assertEquals("wu", bestFriend.getName());
        assertEquals(35, bestFriend.getAge());
        assertEquals(335, bestFriend.getHigh());
        assertEquals(335, bestFriend.getWeight());

        result = attributeCollection.getAttribute("key15");
        assertEquals("wang", result.getName());
        assertEquals(11, result.getAge());
        assertEquals(111, result.getHigh());
        assertEquals(111, result.getWeight());
        colleagues = result.getColleagues();
        colleague = colleagues.get(0);
        assertEquals("li", colleague.getName());
        assertEquals(12, colleague.getAge());
        assertEquals(122, colleague.getHigh());
        assertEquals(112, colleague.getWeight());

        List<PersonAttribute> subColleagues = colleague.getColleagues();
        PersonAttribute subColleague = subColleagues.get(0);
        assertEquals("li", subColleague.getName());
        assertEquals(12, subColleague.getAge());
        assertEquals(122, subColleague.getHigh());
        assertEquals(112, subColleague.getWeight());
        subColleague = subColleagues.get(1);
        assertEquals("yang", subColleague.getName());
        assertEquals(13, subColleague.getAge());
        assertEquals(133, subColleague.getHigh());
        assertEquals(113, subColleague.getWeight());

        colleague = colleagues.get(1);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());

        result = attributeCollection.getAttribute("key16");
        assertEquals("wang", result.getName());
        assertEquals(11, result.getAge());
        assertEquals(111, result.getHigh());
        assertEquals(111, result.getWeight());
        colleagues = result.getColleagues();
        colleague = colleagues.get(0);
        assertEquals("li", colleague.getName());
        assertEquals(12, colleague.getAge());
        assertEquals(122, colleague.getHigh());
        assertEquals(112, colleague.getWeight());

        colleague = colleagues.get(1);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());

        subColleagues = colleague.getColleagues();
        subColleague = subColleagues.get(0);
        assertEquals("li", subColleague.getName());
        assertEquals(12, subColleague.getAge());
        assertEquals(122, subColleague.getHigh());
        assertEquals(112, subColleague.getWeight());
        subColleague = subColleagues.get(1);
        assertEquals("yang", subColleague.getName());
        assertEquals(13, subColleague.getAge());
        assertEquals(133, subColleague.getHigh());
        assertEquals(113, subColleague.getWeight());


        result = attributeCollection.getAttribute("key20");
        assertEquals("li", result.getName());
        assertEquals(20, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        Map<String, PersonAttribute> friendMap = result.getFriends();
        PersonAttribute friend = friendMap.get("subkey1");
        assertNotNull(friend);
        assertEquals("zhang", friend.getName());
        assertEquals(22, friend.getAge());
        assertEquals(175, friend.getHigh());
        assertEquals(160, friend.getWeight());
        friend = friendMap.get("subkey2");
        assertNotNull(friend);
        assertEquals("qian", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());
        friend = friendMap.get("subkey3");
        assertNotNull(friend);
        assertEquals("zhou", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());

        result = attributeCollection.getAttribute("key21");
        assertEquals("li", result.getName());
        assertEquals(20, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        friendMap = result.getFriends();
        friend = friendMap.get("subkey1");
        assertNotNull(friend);
        assertEquals("zhang", friend.getName());
        assertEquals(22, friend.getAge());
        assertEquals(175, friend.getHigh());
        assertEquals(160, friend.getWeight());
        bestFriend = friend.getBestFriend();
        assertEquals("wu", bestFriend.getName());
        assertEquals(35, bestFriend.getAge());
        assertEquals(335, bestFriend.getHigh());
        assertEquals(335, bestFriend.getWeight());

        friend = friendMap.get("subkey2");
        assertNotNull(friend);
        assertEquals("qian", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());
        friend = friendMap.get("subkey3");
        assertNotNull(friend);
        assertEquals("zhou", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());
        bestFriend = friend.getBestFriend();
        assertEquals("li", bestFriend.getName());
        assertEquals(36, bestFriend.getAge());
        assertEquals(335, bestFriend.getHigh());
        assertEquals(335, bestFriend.getWeight());

        result = attributeCollection.getAttribute("key22");
        assertEquals("li", result.getName());
        assertEquals(20, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        friendMap = result.getFriends();
        friend = friendMap.get("subkey1");
        assertNotNull(friend);
        assertEquals("zhang", friend.getName());
        assertEquals(22, friend.getAge());
        assertEquals(175, friend.getHigh());
        assertEquals(160, friend.getWeight());

        friend = friendMap.get("subkey2");
        assertNotNull(friend);
        assertEquals("qian", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());
        bestFriend = friend.getBestFriend();
        assertEquals("li", bestFriend.getName());
        assertEquals(36, bestFriend.getAge());
        assertEquals(335, bestFriend.getHigh());
        assertEquals(335, bestFriend.getWeight());
        friend = friendMap.get("subkey3");
        assertNotNull(friend);
        assertEquals("zhou", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());

        result = attributeCollection.getAttribute("key23");
        assertEquals("li", result.getName());
        assertEquals(20, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        friendMap = result.getFriends();
        friend = friendMap.get("subkey1");
        assertNotNull(friend);
        assertEquals("zhang", friend.getName());
        assertEquals(22, friend.getAge());
        assertEquals(175, friend.getHigh());
        assertEquals(160, friend.getWeight());

        colleagues = friend.getColleagues();
        colleague = colleagues.get(0);
        assertEquals("li", colleague.getName());
        assertEquals(12, colleague.getAge());
        assertEquals(122, colleague.getHigh());
        assertEquals(112, colleague.getWeight());
        colleague = colleagues.get(1);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());

        friend = friendMap.get("subkey2");
        assertNotNull(friend);
        assertEquals("qian", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());
        friend = friendMap.get("subkey3");
        assertNotNull(friend);
        assertEquals("zhou", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());
        colleagues = friend.getColleagues();
        colleague = colleagues.get(0);
        assertEquals("wang", colleague.getName());
        assertEquals(12, colleague.getAge());
        assertEquals(122, colleague.getHigh());
        assertEquals(112, colleague.getWeight());
        colleague = colleagues.get(1);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());


        result = attributeCollection.getAttribute("key24");
        assertEquals("li", result.getName());
        assertEquals(20, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        friendMap = result.getFriends();
        friend = friendMap.get("subkey1");
        assertNotNull(friend);
        assertEquals("zhang", friend.getName());
        assertEquals(22, friend.getAge());
        assertEquals(175, friend.getHigh());
        assertEquals(160, friend.getWeight());

        friend = friendMap.get("subkey2");
        assertNotNull(friend);
        assertEquals("qian", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());
        colleagues = friend.getColleagues();
        colleague = colleagues.get(0);
        assertEquals("li", colleague.getName());
        assertEquals(12, colleague.getAge());
        assertEquals(122, colleague.getHigh());
        assertEquals(112, colleague.getWeight());
        colleague = colleagues.get(1);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());

        friend = friendMap.get("subkey3");
        assertNotNull(friend);
        assertEquals("zhou", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());


        result = attributeCollection.getAttribute("key25");
        assertEquals("li", result.getName());
        assertEquals(20, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        friendMap = result.getFriends();
        friend = friendMap.get("subkey1");
        assertNotNull(friend);
        assertEquals("zhang", friend.getName());
        assertEquals(22, friend.getAge());
        assertEquals(175, friend.getHigh());
        assertEquals(160, friend.getWeight());

        Map<String, PersonAttribute> subFriendMap = friend.getFriends();
        PersonAttribute subFriend = subFriendMap.get("subkey1");
        assertNotNull(subFriend);
        assertEquals("zhang", subFriend.getName());
        assertEquals(22, subFriend.getAge());
        assertEquals(175, subFriend.getHigh());
        assertEquals(160, subFriend.getWeight());
        subFriend = subFriendMap.get("subkey2");
        assertNotNull(subFriend);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());
        subFriend = subFriendMap.get("subkey3");
        assertNotNull(subFriend);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());

        friend = friendMap.get("subkey2");
        assertNotNull(friend);
        assertEquals("qian", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());

        subFriendMap = friend.getFriends();
        subFriend = subFriendMap.get("subkey1");
        assertNotNull(subFriend);
        assertEquals("zhang", subFriend.getName());
        assertEquals(22, subFriend.getAge());
        assertEquals(175, subFriend.getHigh());
        assertEquals(160, subFriend.getWeight());
        subFriend = subFriendMap.get("subkey2");
        assertNotNull(subFriend);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());
        subFriend = subFriendMap.get("subkey3");
        assertNotNull(subFriend);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());

        friend = friendMap.get("subkey3");
        assertNotNull(friend);
        assertEquals("zhou", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());


        result = attributeCollection.getAttribute("key26");
        assertEquals("li", result.getName());
        assertEquals(20, result.getAge());
        assertEquals(180, result.getHigh());
        assertEquals(150, result.getWeight());
        friendMap = result.getFriends();
        friend = friendMap.get("subkey1");
        assertNotNull(friend);
        assertEquals("zhang", friend.getName());
        assertEquals(22, friend.getAge());
        assertEquals(175, friend.getHigh());
        assertEquals(160, friend.getWeight());

        friend = friendMap.get("subkey2");
        assertNotNull(friend);
        assertEquals("qian", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());

        friend = friendMap.get("subkey3");
        assertNotNull(friend);
        assertEquals("zhou", friend.getName());
        assertEquals(21, friend.getAge());
        assertEquals(160, friend.getHigh());
        assertEquals(120, friend.getWeight());

        subFriendMap = friend.getFriends();
        subFriend = subFriendMap.get("subkey1");
        assertNotNull(subFriend);
        assertEquals("zhang", subFriend.getName());
        assertEquals(22, subFriend.getAge());
        assertEquals(175, subFriend.getHigh());
        assertEquals(160, subFriend.getWeight());
        subFriend = subFriendMap.get("subkey2");
        assertNotNull(subFriend);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());
        subFriend = subFriendMap.get("subkey3");
        assertNotNull(subFriend);
        assertEquals("yang", colleague.getName());
        assertEquals(13, colleague.getAge());
        assertEquals(133, colleague.getHigh());
        assertEquals(113, colleague.getWeight());

    }
}
