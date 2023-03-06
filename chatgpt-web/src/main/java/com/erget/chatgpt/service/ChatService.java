package com.erget.chatgpt.service;

import com.erget.chatgpt.dto.ResultDto;

public interface ChatService {
    ResultDto<String> getChatText(String text) throws Exception;

    ResultDto<String> creatImage(String desc);

    ResultDto<String> chat3paint5(String text) throws Exception;

    ResultDto<String> chat3paint50301(String text) throws Exception;

}
