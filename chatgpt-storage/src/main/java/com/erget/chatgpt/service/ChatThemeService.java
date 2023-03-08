package com.erget.chatgpt.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erget.chatgpt.dao.ChatThemeDao;
import com.erget.chatgpt.dto.ResultDto;
import com.erget.chatgpt.dto.ResultDtoFactory;
import com.erget.chatgpt.dto.ThemeQueryReq;
import com.erget.chatgpt.entity.ChatTheme;
import com.erget.chatgpt.util.UserContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 主题数据操作
 */
@Slf4j
@Service
public class ChatThemeService  extends ServiceImpl<ChatThemeDao, ChatTheme> {

    @Autowired
    UserContextUtil userContextUtil;
    public ResultDto checkThemeName(String text) {
        LambdaQueryWrapper<ChatTheme> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<ChatTheme> eq = wrapper.eq(ChatTheme::getThemeName, text);
        ChatTheme one = this.getOne(eq);
        return ResultDtoFactory.toAckData(one == null ? false:true);
    }

    public ResultDto create(String text, String desc) {
        ChatTheme chatTheme = new ChatTheme();
        chatTheme.setThemeDescribe(desc);
        chatTheme.setThemeName(text);
        chatTheme.setCreatedBy(userContextUtil.getUserName());
        chatTheme.setUpdatedBy(userContextUtil.getUserName());
        chatTheme.setCreatedTime(DateUtil.date());
        chatTheme.setUpdatedTime(DateUtil.date());
        chatTheme.setRevision(0);
        chatTheme.setTenantId(0);
        this.save(chatTheme);
        return ResultDtoFactory.toAckData(true);
    }

    public ResultDto queryPage(ThemeQueryReq themeQueryReq) {

        return ResultDtoFactory.toAckData(true);
    }
}
