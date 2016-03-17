package com.example.demo5;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {

	static Gson gson = new Gson();

	// bean转json
	static String beanToJson(Person p) {
		p = new Person("112", "asd");
		return gson.toJson(p);
	}

	// json转bean
	static Person jsonToBean(String json) {
		return gson.fromJson(json, Person.class);

	}

	// 带泛型的list转化为json
	static String beanListToJson(List<Person> list) {
		return gson.toJson(list);
	}

	// json转化为带泛型的list
	static List<Person> jsonToBeanList(String json) {
		return gson.fromJson(json, new TypeToken<List<Person>>() {
		}.getType());
	}

}
