package com.erget.chatgpt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

/**
 * 交流数据;
 * @author : http://www.chiner.pro
 * @date : 2023-3-6
 */
@Data
@TableName("chat_data")
public class ChatData implements Serializable,Cloneable{

    @TableId(type = IdType.AUTO)
    private Long id;
    /** 租户号 */
    private String tenantId ;
    /** 乐观锁 */
    private Integer revision ;
    /** 创建人 */
    private String createdBy ;
    /** 创建时间 */
    private Date createdTime ;
    /** 更新人 */
    private String updatedBy ;
    /** 更新时间 */
    private Date updatedTime ;
    /** 归属用户id */
    private String userId ;
    /** 数据内容 */
    private String content ;
    /** 数据类型;1为提问数据，2为应答数据 */
    private String contentType ;
    /** 令牌号;区分同一个终端发起 */
    private String token ;
    /**
     * 主题id
     */
    private Integer themeId = 0;
}