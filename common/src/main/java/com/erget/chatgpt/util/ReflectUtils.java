package com.erget.chatgpt.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectUtils {

	public static String[] getDeclaredFields(Object object) {
		Class<?> claazz = object.getClass();
		return getDeclaredFields(claazz);
	}

	public static String[] getDeclaredFields(Class<?> claazz) {
		try {
			// 获取实体类的所有属性，返回Field数组
			Field[] fields = claazz.getDeclaredFields();
			String[] strs = new String[fields.length];
			for (int i = 0; i < strs.length; i++) {
				strs[i] = fields[i].getName();
			}
			return strs;
		} catch (Exception e) {
		}
		return null;
	}

	public static Map<String, Class> getDeclaredFieldAndType(Class claazz) {
		try {
			Map<String, Class> map = new HashMap<String, Class>();
			// 获取实体类的所有属性，返回Field数组
			Field[] fields = claazz.getDeclaredFields();
			String[] strs = new String[fields.length];
			for (int i = 0; i < strs.length; i++) {
				map.put(fields[i].getName(), fields[i].getType());
			}
			return map;
		} catch (Exception e) {
		}
		return null;
	}

}
