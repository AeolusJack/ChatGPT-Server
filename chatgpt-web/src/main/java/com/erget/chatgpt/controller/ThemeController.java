package com.erget.chatgpt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.erget.chatgpt.dto.ResultDto;
import com.erget.chatgpt.dto.ResultDtoFactory;
import com.erget.chatgpt.dto.ThemeQueryReq;
import com.erget.chatgpt.entity.ChatTheme;
import com.erget.chatgpt.service.ChatThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/theme")
public class ThemeController {

     @Autowired
     ChatThemeService chatThemeService;

    /**
     * 主题创建
     * @param text
     * @return
     * @throws Exception
     */
    @GetMapping("/create")
    public ResultDto<Boolean> create(String text,String desc) throws Exception {
        return  chatThemeService.create(text,desc);
    }

    /**
     * 主题名称校验
     * @param text
     * @return
     * @throws Exception
     */
    @GetMapping("/check")
    public ResultDto<Boolean> check(String text) throws Exception {
        return  chatThemeService.checkThemeName(text);
    }

    /**
     * 主题查询
     * @param text
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public ResultDto<List<ChatTheme>> list(String text) throws Exception {
        LambdaQueryWrapper<ChatTheme> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<ChatTheme> wrapper = queryWrapper.likeLeft(ChatTheme::getThemeName, text);
        List<ChatTheme> list = chatThemeService.list(wrapper);
        return ResultDtoFactory.toAckData(list);
    }


    /**
     * 主题分页查询
     * @param
     * @return
     * @throws Exception
     */
    @GetMapping("/queryPage")
    public ResultDto<List<ChatTheme>> queryPage(@RequestBody ThemeQueryReq themeQueryReq) throws Exception {
        return  chatThemeService.queryPage(themeQueryReq);
    }

}
