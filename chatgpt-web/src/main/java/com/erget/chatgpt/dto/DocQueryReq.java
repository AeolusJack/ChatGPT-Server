package com.erget.chatgpt.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DocQueryReq {
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 截止时间
     */
    private String endTime;

    /**
     * 内容类型
     */
    private String contentType;
}
