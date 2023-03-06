package com.erget.chatgpt.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.io.Serializable;
import java.util.Date;

 /**
 * 用户表;
 * @author : http://www.chiner.pro
 * @date : 2023-3-6
 */

@ApiModel(value = "用户表",description = "")
@TableName("sys_suer")
public class SysUser implements Serializable,Cloneable{
    /** 租户号 */
    @ApiModelProperty(name = "租户号",notes = "")
    private String tenantId ;
    /** 乐观锁 */
    @ApiModelProperty(name = "乐观锁",notes = "")
    private Integer revision ;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    private String createdBy ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    private Date createdTime ;
    /** 更新人 */
    @ApiModelProperty(name = "更新人",notes = "")
    private String updatedBy ;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    private Date updatedTime ;
    /** 主键 */

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "主键",notes = "")
    private Integer id ;
    /** 用户名 */
    @ApiModelProperty(name = "用户名",notes = "")
    private String userName ;
    /** 密码 */
    @ApiModelProperty(name = "密码",notes = "")
    private String password ;
    /** 电话 */
    @ApiModelProperty(name = "电话",notes = "")
    private String phone ;
    /** 邮箱 */
    @ApiModelProperty(name = "邮箱",notes = "")
    private String email ;
    /** 地址 */
    @ApiModelProperty(name = "地址",notes = "")
    private String address ;
    /** 性别 */
    @ApiModelProperty(name = "性别",notes = "")
    private String sex ;
    /** 头像 */
    @ApiModelProperty(name = "头像",notes = "")
    private String icon ;
    /** 机构id */
    @ApiModelProperty(name = "机构id",notes = "")
    private Integer groupId ;

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
    /** 主键 */
    public Integer getId(){
        return this.id;
    }
    /** 主键 */
    public void setId(Integer id){
        this.id=id;
    }
    /** 用户名 */
    public String getUserName(){
        return this.userName;
    }
    /** 用户名 */
    public void setUserName(String userName){
        this.userName=userName;
    }
    /** 密码 */
    public String getPassword(){
        return this.password;
    }
    /** 密码 */
    public void setPassword(String password){
        this.password=password;
    }
    /** 电话 */
    public String getPhone(){
        return this.phone;
    }
    /** 电话 */
    public void setPhone(String phone){
        this.phone=phone;
    }
    /** 邮箱 */
    public String getEmail(){
        return this.email;
    }
    /** 邮箱 */
    public void setEmail(String email){
        this.email=email;
    }
    /** 地址 */
    public String getAddress(){
        return this.address;
    }
    /** 地址 */
    public void setAddress(String address){
        this.address=address;
    }
    /** 性别 */
    public String getSex(){
        return this.sex;
    }
    /** 性别 */
    public void setSex(String sex){
        this.sex=sex;
    }
    /** 头像 */
    public String getIcon(){
        return this.icon;
    }
    /** 头像 */
    public void setIcon(String icon){
        this.icon=icon;
    }
    /** 机构id */
    public Integer getGroupId(){
        return this.groupId;
    }
    /** 机构id */
    public void setGroupId(Integer groupId){
        this.groupId=groupId;
    }
}