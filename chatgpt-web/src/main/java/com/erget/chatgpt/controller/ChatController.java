package com.erget.chatgpt.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.erget.chatgpt.api.service.ChatGPTService;
import com.erget.chatgpt.dto.ResultDto;
import com.erget.chatgpt.dto.ResultDtoFactory;
import com.erget.chatgpt.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/chat")
public class ChatController {


    @Autowired
    ChatService chatService;

    @GetMapping("/getChatText")
    public ResultDto<String> getChatText(String text) throws Exception {
       return  chatService.getChatText(text);
    }

    @GetMapping("/creatImage")
    public ResultDto<String> creatImage(String desc) throws Exception {
        return  chatService.creatImage(desc);

    }


    @GetMapping("/caht3paint5")
    public ResultDto<String> chat3paint5(String text) throws Exception {
        return  chatService.chat3paint5(text);
    }

    @GetMapping("/caht3paint50301")
    public ResultDto<String> chat3paint50301(String text) throws Exception {
        return  chatService.chat3paint50301(text);
    }

}
