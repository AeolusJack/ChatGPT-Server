package com.erget.chatgpt.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erget.chatgpt.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

}
