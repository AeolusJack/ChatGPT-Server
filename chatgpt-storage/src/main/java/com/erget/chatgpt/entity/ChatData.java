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

    /** 租户号 */
    public String getTenantId(){
        return this.tenantId;
    }
    /** 租户号 */
    public void setTenantId(String tenantId){
        this.tenantId=tenantId;
    }
    /** 乐观锁 */
    public Integer getRevision(){
        return this.revision;
    }
    /** 乐观锁 */
    public void setRevision(Integer revision){
        this.revision=revision;
    }
    /** 创建人 */
    public String getCreatedBy(){
        return this.createdBy;
    }
    /** 创建人 */
    public void setCreatedBy(String createdBy){
        this.createdBy=createdBy;
    }
    /** 创建时间 */
    public Date getCreatedTime(){
        return this.createdTime;
    }
    /** 创建时间 */
    public void setCreatedTime(Date createdTime){
        this.createdTime=createdTime;
    }
    /** 更新人 */
    public String getUpdatedBy(){
        return this.updatedBy;
    }
    /** 更新人 */
    public void setUpdatedBy(String updatedBy){
        this.updatedBy=updatedBy;
    }
    /** 更新时间 */
    public Date getUpdatedTime(){
        return this.updatedTime;
    }
    /** 更新时间 */
    public void setUpdatedTime(Date updatedTime){
        this.updatedTime=updatedTime;
    }
    /** 归属用户id */
    public String getUserId(){
        return this.userId;
    }
    /** 归属用户id */
    public void setUserId(String userId){
        this.userId=userId;
    }
    /** 数据内容 */
    public String getContent(){
        return this.content;
    }
    /** 数据内容 */
    public void setContent(String content){
        this.content=content;
    }

    public String getContentType(){
        return this.contentType;
    }

    public void setContentType(String contentType){
        this.contentType=contentType;
    }
    /** 令牌号;区分同一个终端发起 */
    public String getToken(){
        return this.token;
    }
    /** 令牌号;区分同一个终端发起 */
    public void setToken(String token){
        this.token=token;
    }
}