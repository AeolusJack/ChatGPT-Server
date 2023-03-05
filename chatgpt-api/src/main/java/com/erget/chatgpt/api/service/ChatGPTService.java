package com.erget.chatgpt.api.service;

import com.erget.chatgpt.dto.ResultDto;

public interface ChatGPTService {

    /**
     * 获取模型列表
     * @return
     */
     ResultDto<String>  listModel();

    /**
     * 问题答复 和 文本完善
     * @param text
     * @return
     */
     ResultDto<String> getText003Chat(String text);

    /**
     * 根据描述生成图片
     * @param desc
     * @return
     */
     ResultDto<String> createImage(String desc);

    ResultDto<String> chat3paint5(String text);

    ResultDto<String> chat3paint50301(String text);
}
