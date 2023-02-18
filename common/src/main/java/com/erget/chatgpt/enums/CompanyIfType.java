/**
 * @ProjectName idea-ams-core
 * @FileName FormStatus.java
 * @PackageName:com.idea.ams.domain.enums
 * @author ku
 * @date 2015年7月30日下午3:47:00
 * @Copyright (c) 2015,kuyonggang@ideatech.info All Rights Reserved.
 * @since 1.0.0
 */
package com.erget.chatgpt.enums;

import org.apache.commons.lang.StringUtils;

/**
 * @author zoulang
 * @ClassName: CompanyAcctType
 * @Description: 判断是否类型
 * @date 2015年11月13日 上午9:55:09
 */
public enum CompanyIfType {

    Yes("1"),

    No("0");

    private String value;

    CompanyIfType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static CompanyIfType str2enum(String enmuStr) {
        if (StringUtils.isBlank(enmuStr)) {
            return null;
        }
        if (enmuStr.equals("Yes")) {
            return CompanyIfType.Yes;
        } else if (enmuStr.equals("No")) {
            return CompanyIfType.No;
        } else {
            return null;
        }
    }

    public static CompanyIfType getValue(String name) {
        CompanyIfType[] values = CompanyIfType.values();
        for (CompanyIfType value : values) {
            if (StringUtils.equals(value.getValue(), name)) {
                return value;
            }
        }
        return null;
    }
}
