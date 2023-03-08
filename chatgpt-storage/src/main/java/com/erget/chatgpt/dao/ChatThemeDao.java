package com.erget.chatgpt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erget.chatgpt.entity.ChatTheme;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatThemeDao extends BaseMapper<ChatTheme> {
}
