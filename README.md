一个以XML为载体的通用属性配置模块，可以通过唯一标记值key获取配置，目前支持2层嵌套配置，配置支持：

单值

包括java基本数据类型对象:@Boolean,@Byte,@Short,@Integer,@Long,@Float,@Double,@Character和@String字符串类，及定义在包名路径下attributepath目录下继承com.deepindex.attributeset.AttributeParser.Attribute类的自定义子类，自定义子类可以嵌套使用上述基本类型和String类型，及自定义类型。

列表

以单值、列表、键值对为value的列表。

键值对

以String为key，以单值、列表、键值对为value的键值对。


例子：

XML配置

    <attribute key="key0" type="boolean" value="true" />

code（fileName为XML配置文件名）

    AttributeCollection attributeCollection = new AttributeCollection();
    AttributeParser parser = new AttributeParser(context, attributeCollection);
    try (InputStream inputStream = new ByteArrayInputStream(
            fileName.getBytes(StandardCharsets.UTF_8))) {
        parser.parse(inputStream);
    } catch (IOException | XmlPullParserException e) {
        Log.e("AttributeCollection", "parse error", e);
    }

    //true
    boolean value = attributeCollection.getBoolean("key0");


XML配置

    <attribute_list key="key1" type="String" >
      <sub value="hello" />
      <sub value="world" />
    </attribute_list>

code（fileName为XML配置文件名）

    AttributeCollection attributeCollection = new AttributeCollection();
    AttributeParser parser = new AttributeParser(context, attributeCollection);
    try (InputStream inputStream = new ByteArrayInputStream(
            fileName.getBytes(StandardCharsets.UTF_8))) {
        parser.parse(inputStream);
    } catch (IOException | XmlPullParserException e) {
        Log.e("AttributeCollection", "parse error", e);
    }

    ArrayList<String> list = attributeCollection.getStringArrayList("key1");
    //hello
    String value = list.get(0);
    //world
    value = list.get(1);

    
XML配置

    <attribute_map key="key2" type="int" >
        <sub key="subkey1" value="11" />
        <sub key="subkey2" value="22" />
    </attribute_map>

code（fileName为XML配置文件名）

    AttributeCollection attributeCollection = new AttributeCollection();
    AttributeParser parser = new AttributeParser(context, attributeCollection);
    try (InputStream inputStream = new ByteArrayInputStream(
            fileName.getBytes(StandardCharsets.UTF_8))) {
        parser.parse(inputStream);
    } catch (IOException | XmlPullParserException e) {
        Log.e("AttributeCollection", "parse error", e);
    }

    ArrayMap<String, Integer> map = attributeCollection.getIntegerArrayMap("key2");
    //11
    int value = map.get("subkey1");
    //22
    value = map.get("subkey2");


XML配置

    <attribute_map key="key4" type="PersonAttribute" >
        <sub key="subkey1" name="li" age="20" high="180" weight="70" />
        <sub key="subkey2" name="wang" age="21" high="185" weight="80" />
    </attribute_map>

code（fileName为XML配置文件名）

	public class PersonAttribute extends Attribute {
		private String mName;
		private int mAge;
		private int mHigh;
		private int mWeight;

		public PersonAttribute() {
		}

		public PersonAttribute(String name, int age, int high, int weight) {
			mName = name;
			mAge = age;
			mHigh = high;
			mWeight = weight;
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
	}


    AttributeCollection attributeCollection = new AttributeCollection();
    AttributeParser parser = new AttributeParser(context, attributeCollection);
    try (InputStream inputStream = new ByteArrayInputStream(
            fileName.getBytes(StandardCharsets.UTF_8))) {
        parser.parse(inputStream);
    } catch (IOException | XmlPullParserException e) {
        Log.e("AttributeCollection", "parse error", e);
    }

    ArrayMap<String, PersonAttribute> map = attributeCollection.getAttributeArrayMap("key4");
    PersonAttribute value = map.get("subkey1");
    <sub key="subkey1" name="li" age="20" high="180" weight="70" />
    <sub key="subkey2" name="wang" age="21" high="185" weight="80" />
    //li
    String name = value.getName();
    //20
    int age = value.getAge();
    //180
    int high = value.getHigh();
    //70
    int weight = value.getWeight();

更多例子可以参考assets文件夹下attributetest.xml

