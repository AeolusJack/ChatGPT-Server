package com.erget.chatgpt.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.erget.chatgpt.api.service.ChatGPTService;
import com.erget.chatgpt.dto.ResultDto;
import com.erget.chatgpt.dto.ResultDtoFactory;
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
    public ResultDto<String> getChatText(String text) throws Exception {
        log.info("请求参数：{} ",  text);
        ResultDto<String> text003Chat =  null;
        try {
            text003Chat = chatGPTService.getText003Chat(text);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ResultDtoFactory.toAckData("暂时请求不到chatGPT服务");
        }
        JSONObject entries = JSONUtil.parseObj(text003Chat.getData());
        log.info(entries.toString());
        JSONArray choices = (JSONArray) entries.get("choices");
        JSONObject jsonObject = (JSONObject)choices.get(0);
        String textResult = (String)jsonObject.get("text");
        log.info(textResult);

        return ResultDtoFactory.toAckData(textResult);
    }

    @GetMapping("/creatImage")
    public ResultDto<String> creatImage(String desc) throws Exception {
        log.info("请求参数：{} ",  desc);
        ResultDto<String> image = chatGPTService.createImage(desc);
        JSONObject entries = JSONUtil.parseObj(image.getData());
        JSONArray data = (JSONArray) entries.get("data");
        //JSONObject jsonObject = (JSONObject)choices.get(0);
       // String textResult = (String)jsonObject.get("url");
        String toString = data.toString();
        log.info(toString);
        return ResultDtoFactory.toAckData(toString);
    }


    @GetMapping("/caht3paint5")
    public ResultDto<String> chat3paint5(String text) throws Exception {
        log.info("请求参数：{} ",  text);
        ResultDto<String> text003Chat =  null;
        try {
            text003Chat = chatGPTService.chat3paint50301(text);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ResultDtoFactory.toAckData("暂时请求不到chatGPT服务");
        }
        JSONObject entries = JSONUtil.parseObj(text003Chat.getData());
        log.info(entries.toString());
        JSONArray choices = (JSONArray) entries.get("choices");
        JSONObject jsonObject = (JSONObject)choices.get(0);
        JSONObject message = jsonObject.getJSONObject("message");
        String content = message.getStr("content");
        log.info(content);
        return ResultDtoFactory.toAckData(content);
    }

    @GetMapping("/caht3paint50301")
    public ResultDto<String> chat3paint50301(String text) throws Exception {
        log.info("请求参数：{} ",  text);
        ResultDto<String> text003Chat =  null;
        try {
            text003Chat = chatGPTService.chat3paint50301(text);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ResultDtoFactory.toAckData("暂时请求不到chatGPT服务");
        }
        JSONObject entries = JSONUtil.parseObj(text003Chat.getData());
        log.info(entries.toString());
        JSONArray choices = (JSONArray) entries.get("choices");
        JSONObject jsonObject = (JSONObject)choices.get(0);
        JSONObject message = jsonObject.getJSONObject("message");
        String content = message.getStr("content");
        log.info(content);
        return ResultDtoFactory.toAckData(content);
    }

}
