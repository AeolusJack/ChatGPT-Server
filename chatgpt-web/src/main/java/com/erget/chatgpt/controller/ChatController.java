package com.erget.chatgpt.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.erget.chatgpt.api.service.ChatGPTService;
import com.erget.chatgpt.dto.ResultDto;
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
    ChatGPTService chatGPTService;


    @GetMapping("/getChatText")
    public String getChatText(String text) throws Exception {
        log.info("请求参数：{} ",  text);
        ResultDto<String> text003Chat = chatGPTService.getText003Chat(text);
        JSONObject entries = JSONUtil.parseObj(text003Chat.getData());
        log.info(entries.toString());
        JSONArray choices = (JSONArray) entries.get("choices");
        JSONObject jsonObject = (JSONObject)choices.get(0);
        String textResult = (String)jsonObject.get("text");
        log.info(textResult);
        return textResult;
    }

    @GetMapping("/creatImage")
    public String creatImage(String desc) throws Exception {
        log.info("请求参数：{} ",  desc);
        ResultDto<String> image = chatGPTService.createImage(desc);
        JSONObject entries = JSONUtil.parseObj(image.getData());
        JSONArray data = (JSONArray) entries.get("data");
        //JSONObject jsonObject = (JSONObject)choices.get(0);
       // String textResult = (String)jsonObject.get("url");
        String toString = data.toString();
        log.info(toString);
        return toString;
    }


}
