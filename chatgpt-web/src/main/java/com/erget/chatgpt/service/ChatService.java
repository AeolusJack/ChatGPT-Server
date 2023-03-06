package com.erget.chatgpt.service;

import com.erget.chatgpt.dto.ResultDto;

public interface ChatService {
    ResultDto<String> getChatText(String text);

    ResultDto<String> creatImage(String desc);

    ResultDto<String> chat3paint5(String text);

    ResultDto<String> chat3paint50301(String text);

}
