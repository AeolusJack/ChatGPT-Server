package com.erget.chatgpt.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erget.chatgpt.dao.ChatDataDao;
import com.erget.chatgpt.entity.ChatData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 对话数据存储
 */
@Slf4j
@Service
public class ChatDataStorageService extends ServiceImpl<ChatDataDao, ChatData> {

}
