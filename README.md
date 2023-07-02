一个以XML为载体的通用属性配置模块，可以通过唯一标记值key获取配置，目前支持2层嵌套配置，配置支持：

单值

包括java基本数据类型对象:@Boolean,@Byte,@Short,@Integer,@Long,@Float,@Double,@Character和@String字符串类，及定义在包名路径下configpath目录下继承com.deepindex.configset.ConfigParser.Config类的自定义子类，自定义子类可以嵌套使用上述基本类型和String类型，及自定义类型。

列表

以单值、列表、键值对为value的列表。

键值对

以String为key，以单值、列表、键值对为value的键值对。


例子：

XML配置

    <config key="key0" type="boolean" value="true" />

code（fileName为XML配置文件名）

    ConfigCollection configCollection = new ConfigCollection();
    ConfigParser parser = new ConfigParser(context, configCollection);
    try (InputStream inputStream = new ByteArrayInputStream(
            fileName.getBytes(StandardCharsets.UTF_8))) {
        parser.parse(inputStream);
    } catch (IOException | XmlPullParserException e) {
        Log.e("ConfigCollection", "parse error", e);
    }

    //true
    boolean value = configCollection.getBoolean("key0");


XML配置

    <config_list key="key1" type="String" >
      <sub value="hello" />
      <sub value="world" />
    </config_list>

code（fileName为XML配置文件名）

    ConfigCollection configCollection = new ConfigCollection();
    ConfigParser parser = new ConfigParser(context, configCollection);
    try (InputStream inputStream = new ByteArrayInputStream(
            fileName.getBytes(StandardCharsets.UTF_8))) {
        parser.parse(inputStream);
    } catch (IOException | XmlPullParserException e) {
        Log.e("ConfigCollection", "parse error", e);
    }

    ArrayList<String> list = configCollection.getStringArrayList("key1");
    //hello
    String value = list.get(0);
    //world
    value = list.get(1);

    
XML配置

    <config_map key="key2" type="int" >
        <sub key="subkey1" value="11" />
        <sub key="subkey2" value="22" />
    </config_map>

code（fileName为XML配置文件名）

    ConfigCollection configCollection = new ConfigCollection();
    ConfigParser parser = new ConfigParser(context, configCollection);
    try (InputStream inputStream = new ByteArrayInputStream(
            fileName.getBytes(StandardCharsets.UTF_8))) {
        parser.parse(inputStream);
    } catch (IOException | XmlPullParserException e) {
        Log.e("ConfigCollection", "parse error", e);
    }

    ArrayMap<String, Integer> map = configCollection.getIntegerArrayMap("key2");
    //11
    int value = map.get("subkey1");
    //22
    value = map.get("subkey2");


XML配置

    <config_map key="key4" type="PersonConfig" >
        <sub key="subkey1" name="li" age="20" high="180" weight="70" />
        <sub key="subkey2" name="wang" age="21" high="185" weight="80" />
    </config_map>

code（fileName为XML配置文件名）

	public class PersonConfig extends Config {
		private String mName;
		private int mAge;
		private int mHigh;
		private int mWeight;

		public PersonConfig() {
		}

		public PersonConfig(String name, int age, int high, int weight) {
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


    ConfigCollection configCollection = new ConfigCollection();
    ConfigParser parser = new ConfigParser(context, configCollection);
    try (InputStream inputStream = new ByteArrayInputStream(
            fileName.getBytes(StandardCharsets.UTF_8))) {
        parser.parse(inputStream);
    } catch (IOException | XmlPullParserException e) {
        Log.e("ConfigCollection", "parse error", e);
    }

    ArrayMap<String, PersonConfig> map = configCollection.getConfigArrayMap("key4");
    PersonConfig value = map.get("subkey1");
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

更多例子可以参考assets文件夹下configtest.xml

