package com.erget.chatgpt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 主题
 */
@Data
@TableName("chat_theme")
public class ChatTheme {

    /** 租户号 */
    private Integer tenantId ;
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
    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Integer id ;
    /** 用户ID */
    private String userId ;
    /** 主题描述 */
    private String themeDescribe ;
    /** 主题名称 */
    private String themeName ;
    /**父主题id，只有1级表示该主题无父主题 类似格式 1-11-23 **/
    private String parentId;

}
