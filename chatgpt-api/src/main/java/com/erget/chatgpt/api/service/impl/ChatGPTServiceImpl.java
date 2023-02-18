package com.erget.chatgpt.api.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.erget.chatgpt.api.service.ChatGPTService;
import com.erget.chatgpt.api.service.constant.ChatGPTModel;
import com.erget.chatgpt.dto.ResultDto;
import com.erget.chatgpt.dto.ResultDtoFactory;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTServiceImpl implements ChatGPTService {
    @Override
    public ResultDto<String> listModel() {
        HttpRequest request = HttpUtil.createGet("https://api.openai.com/v1/models");
        HttpRequest httpRequest = request.header("Authorization", "Bearer "+ChatGPTModel.API_KEY);
        HttpResponse execute = httpRequest.execute();
        String body = execute.body();
        return ResultDtoFactory.toAck("请求成功",body);
    }


    @Override
    public ResultDto<String> getText003Chat(String text) {
        HttpRequest post = HttpUtil.createPost(ChatGPTModel.TEXT_003_MODEL_URL);
        HttpRequest httpRequest = post
                .header("Authorization", "Bearer "+ChatGPTModel.API_KEY)
                .header("Content-Type"," application/json");
        JSONObject obj = JSONUtil.createObj();
        obj.putOnce("model",ChatGPTModel.TEXT_003);
        obj.putOnce("prompt",text);
        obj.putOnce("max_tokens",2000);
        obj.putOnce("temperature",0);
        HttpResponse execute = httpRequest.body(obj.toString()).execute();
        return ResultDtoFactory.toAck("请求成功",execute.body());
    }

    @Override
    public ResultDto<String> createImage(String desc) {
        HttpRequest post = HttpUtil.createPost(ChatGPTModel.IMAGE_CREATE_MODEL_URL);
        HttpRequest httpRequest = post
                .header("Authorization", "Bearer "+ChatGPTModel.API_KEY)
                .header("Content-Type"," application/json");
        JSONObject obj = JSONUtil.createObj();
        obj.putOnce("prompt",desc);
        obj.putOnce("n",2);
        obj.putOnce("size","1024x1024");
        HttpResponse execute = httpRequest.body(obj.toString()).execute();
        return ResultDtoFactory.toAck("请求成功",execute.body());
    }


}
