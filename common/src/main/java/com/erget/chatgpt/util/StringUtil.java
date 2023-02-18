package com.erget.chatgpt.util;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class StringUtil {
	public final static Map<String,String> mapCache = new HashMap<>();
	/**
	 *@Description: 缓存转化后的字段
	 *@Param: [key]
	 *@return: java.lang.String
	 *@Author: wanghongjie
	 *@date: 2018/9/17
	 */
	public static String changeColumnToDataInCache(String key){
		if(mapCache.containsKey(key)){
			return mapCache.get(key);
		}else{
			mapCache.put(key, StringUtil.changeColumnToData(key));
			return mapCache.get(key);
		}
	}

	public static void clearCache(){
		mapCache.clear();
	}

	public static String changeColumnToData(String str) {
		StringBuilder builder = new StringBuilder(str.replace('.', '_'));

		for (int i = 1; i < builder.length() - 1; i++) {
			if (isUnderscoreRequired(builder.charAt(i - 1), builder.charAt(i),
					builder.charAt(i + 1))) {
				builder.insert(i++, '_');
			}
		}
		return builder.toString().toLowerCase(Locale.ROOT);
	}

	private static boolean isUnderscoreRequired(char before, char current, char after) {
		return Character.isLowerCase(before) && Character.isUpperCase(current) && Character.isLowerCase(after);
	}

	public static boolean containAnyOne(String base,String...filter) {
		for (String str : filter) {
			if(StringUtils.contains(base, str)) {
				return true;
			}
		}
		return false;
	}

	public static String charAtByIndex(String str,int index){
		return StringUtils.substring(str,0,index);
	}
	/**
	 * 将null转换成空字符
	 */
	public static String nullToString(String parameter){ String str="";if(parameter==null){ str=""; }else{ str=parameter; }return str;}
}