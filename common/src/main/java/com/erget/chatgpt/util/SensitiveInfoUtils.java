package com.erget.chatgpt.util;

import org.apache.commons.lang.StringUtils;

/**
 * 数据脱敏工具类
 */
public class SensitiveInfoUtils {
    /**
     * [身份证号] 显示前六位，最后两位，其他隐藏。共计18位或者15位。<例子：152728*********62>
     *
     * @param num
     * @return
     */
    public static String idCardNum(String num) {
        int leftLength = 6;
        int rightLength = 2;
        if (StringUtils.isBlank(num)) {
            return "";
        } else if (num.length() <= leftLength + rightLength) {
            return num;
        } else {
            return StringUtils.left(num, leftLength).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, rightLength), StringUtils.length(num), "*"), "******"));
        }
    }

    /**
     * [手机号码] 前三位，后两位，其他隐藏<例子:138********34>
     *
     * @param num
     * @return
     */
    public static String mobilePhone(String num) {
        int leftLength = 3;
        int rightLength = 2;
        if (StringUtils.isBlank(num)) {
            return "";
        } else if (num.length() <= leftLength + rightLength) {
            return num;
        } else {
            return StringUtils.left(num, leftLength).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, rightLength), StringUtils.length(num), "*"), "***"));
        }
    }


    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     *
     * @param fullName
     * @return
     */
    public static String chineseName(String fullName) {
        int leftLength = 1;
        if (StringUtils.isBlank(fullName)) {
            return "";
        } else if (fullName.length() <= leftLength) {
            return fullName;
        } else {
            String name = StringUtils.left(fullName, leftLength);
            return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
        }
    }

    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     *
     * @param familyName 姓
     * @param givenName 名
     * @return
     */
    public static String chineseName(String familyName, String givenName) {
        if (StringUtils.isBlank(familyName) || StringUtils.isBlank(givenName)) {
            return "";
        }
        return chineseName(familyName + givenName);
    }

    /**
     * [固定电话] 后四位，其他隐藏<例子：****1234>
     *
     * @param num
     * @return
     */
    public static String fixedPhone(String num) {
        int rightLength = 4;
        if (StringUtils.isBlank(num)) {
            return "";
        } else if (num.length() <= rightLength) {
            return num;
        } else {
            return StringUtils.leftPad(StringUtils.right(num, rightLength), StringUtils.length(num), "*");
        }
    }

    /**
     * [地址] 只显示到地区，不显示详细地址；我们要对个人信息增强保护<例子：北京市海淀区****>
     *
     * @param address
     * @param sensitiveSize 敏感信息长度
     * @return
     */
    public static String address(String address, int sensitiveSize) {
        if (StringUtils.isBlank(address)) {
            return "";
        } else if (address.length() <= sensitiveSize) {//隐藏后70%的字符
            sensitiveSize = address.length() * 7 / 10;
        }
        int length = StringUtils.length(address);
        return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
    }

    /**
     * [电子邮箱] 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示<例子:g**@163.com>
     *
     * @param email
     * @return
     */
    public static String email(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        int index = StringUtils.indexOf(email, "@");
        if (index <= 1) {
            return email;
        } else {
            return StringUtils.rightPad(StringUtils.left(email, 1), index, "*").concat(StringUtils.mid(email, index, StringUtils.length(email)));
        }
    }

    /**
     * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:6222600**********1234>
     *
     * @param cardNum
     * @return
     */
    public static String bankCard(String cardNum) {
        int leftLength = 6;
        int rightLength = 4;
        if (StringUtils.isBlank(cardNum)) {
            return "";
        } else if (cardNum.length() <= leftLength + rightLength) {
            return cardNum;
        } else {
            return StringUtils.left(cardNum, leftLength).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, rightLength), StringUtils.length(cardNum), "*"), "******"));
        }
    }

    /**
     * [公司开户银行联号] 公司开户银行联行号,显示前两位，其他用星号隐藏，每位1个星号<例子:12********>
     *
     * @param code
     * @return
     */
    public static String cnapsCode(String code) {
        int leftLength = 2;
        if (StringUtils.isBlank(code)) {
            return "";
        } else if (code.length() <= leftLength) {
            return code;
        } else {
            return StringUtils.rightPad(StringUtils.left(code, leftLength), StringUtils.length(code), "*");
        }
    }

    /**
     * 泛指身份证、军官证等证件号码的脱敏处理（只展示前两位后两位）
     * @param idNum
     * @return
     */
    public static String idNum(String idNum){
        int leftLength = 2;
        int rightLength = 2;
        if (StringUtils.isBlank(idNum)) {
            return "";
        } else if (idNum.length() <= leftLength + rightLength) {
            return StringUtils.rightPad(StringUtils.left(idNum, 1), StringUtils.length(idNum), "*");
        } else {
            return StringUtils.left(idNum, leftLength).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(idNum, rightLength), StringUtils.length(idNum), "*"), "***"));
        }
    }
}
