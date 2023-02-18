package com.erget.chatgpt.util;

import java.util.Random;

public class RandomUtil {
	private static final String STR_BASE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final String STR_NUMBER_BASE = "0123456789";
	public static String getMixStr(int length){
		int len = STR_BASE.length();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<length;i++) {
			sb.append(STR_BASE.charAt(new Random().nextInt(len)));
		}
		return sb.toString();
	}
	public static String getMixNumStr(int length){
		int len = STR_NUMBER_BASE.length();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<length;i++) {
			sb.append(STR_NUMBER_BASE.charAt(new Random().nextInt(len)));
		}
		return sb.toString();
	}
}
