package com.example.demo5;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {

	static Gson gson = new Gson();

	// beanתjson
	static String beanToJson(Person p) {
		p = new Person("112", "asd");
		return gson.toJson(p);
	}

	// jsonתbean
	static Person jsonToBean(String json) {
		return gson.fromJson(json, Person.class);

	}

	// �����͵�listת��Ϊjson
	static String beanListToJson(List<Person> list) {
		return gson.toJson(list);
	}

	// jsonת��Ϊ�����͵�list
	static List<Person> jsonToBeanList(String json) {
		return gson.fromJson(json, new TypeToken<List<Person>>() {
		}.getType());
	}

}
