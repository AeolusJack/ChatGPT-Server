package com.erget.chatgpt.util;

import org.apache.commons.lang.StringUtils;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * 
 * @author zoulang
 *
 */
public class ExceptionUtils {

	/**
	 * 判断异常类型是否属于网络异常
	 * 
	 * @param e
	 * @return
	 */
	public static boolean isNetExption(Exception e) {
		String exceptionMsg = e.getMessage();
		if (e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof SocketException) {
			return true;
		} else if (e instanceof SocketTimeoutException) {
			return true;
		} else if (StringUtils.isNotBlank(exceptionMsg)
				&& (exceptionMsg.contains("网络不通") || exceptionMsg.contains("网络超时") || exceptionMsg.contains("网络异常"))) {
			return true;
		}
		return false;
	}

	/**
	 * 是否网络不通
	 * 
	 * @param e
	 * @return
	 */
	public static boolean isconnectException(Exception e) {
		if (e instanceof ConnectException || e instanceof SocketException) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否网络不通
	 * 
	 * @param e
	 * @return
	 */
	public static boolean isconnectException(Throwable e) {
		if (e instanceof ConnectException || e instanceof SocketException) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否网络超时
	 * 
	 * @param e
	 * @return
	 */
	public static boolean isTimeoutException(Exception e) {
		if (e instanceof SocketTimeoutException || e instanceof SocketTimeoutException) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否网络超时
	 * 
	 * @param e
	 * @return
	 */
	public static boolean isTimeoutException(Throwable e) {
		if (e instanceof SocketTimeoutException || e instanceof SocketTimeoutException) {
			return true;
		} else {
			return false;
		}
	}
}
