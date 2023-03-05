package com.erget.chatgpt.api.service.constant;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * ChatGPT的模型
 */
@Slf4j
@Component
public  class ChatGPTModel {

    @Value("${chatGpt.api.key.url}")
    private String keyUrl;
    public  String getApiKey() {
        try {
            List<String> strings = FileUtil.readLines(keyUrl, "utf-8");
             API_KEY = strings.get(0).trim();
        } catch (Exception e) {
            log.error("获取API key 错误：",e);
            throw new RuntimeException(e);
        }
        return API_KEY;
    }

    /**
     * chatgpt api-key
     */
    private  String API_KEY = "";


    /**
     * 文本模型
     * 补全句子 / 回答问题
     */
    public static final String TEXT_003 = "text-davinci-003";

    /**
     * Request：
     * curl https://api.openai.com/v1/completions \
     * -H "Content-Type: application/json" \
     * -H "Authorization: Bearer YOUR_API_KEY" \
     * -d '{"model": "text-davinci-003", "prompt": "Say this is a test", "temperature": 0, "max_tokens": 7}'
     *
     * Response：
     * {
     *     "id": "cmpl-GERzeJQ4lvqPk8SkZu4XMIuR",
     *     "object": "text_completion",
     *     "created": 1586839808,
     *     "model": "text-davinci:003",
     *     "choices": [
     *         {
     *             "text": "\n\nThis is indeed a test",
     *             "index": 0,
     *             "logprobs": null,
     *             "finish_reason": "length"
     *         }
     *     ],
     *     "usage": {
     *         "prompt_tokens": 5,
     *         "completion_tokens": 7,
     *         "total_tokens": 12
     *     }
     * }
     *
     * text003模型地址  POST
     */
    public static final String TEXT_003_MODEL_URL = "https://api.openai.com/v1/completions";

    /**
     * 文本模型
     * 进行编辑、改错，使更完善正确等等，根据说明对文本进行操作  POST
     */
    public static final String TEXT_EDIT_001 ="text-davinci-edit-001";

    /**
     * curl https://api.openai.com/v1/edits \
     *   -H 'Content-Type: application/json' \
     *   -H 'Authorization: Bearer YOUR_API_KEY' \
     *   -d '{
     *   "model": "text-davinci-edit-001",
     *   "input": "What day of the wek is it?",
     *   "instruction": "Fix the spelling mistakes"
     * }'
     *
     * {
     *   "object": "edit",
     *   "created": 1589478378,
     *   "choices": [
     *     {
     *       "text": "What day of the week is it?",
     *       "index": 0,
     *     }
     *   ],
     *   "usage": {
     *     "prompt_tokens": 25,
     *     "completion_tokens": 32,
     *     "total_tokens": 57
     *   }
     * }
     * text001模型地址  POST
     */
    public static final String TEXT_EDIT_MODEL_URL = "https://api.openai.com/v1/edits";

    /**
     * curl https://api.openai.com/v1/images/generations \
     *   -H 'Content-Type: application/json' \
     *   -H 'Authorization: Bearer YOUR_API_KEY' \
     *   -d '{
     *   "prompt": "A cute baby sea otter",
     *   "n": 2,
     *   "size": "1024x1024"
     * }'
     *
     * {
     *   "created": 1589478378,
     *   "data": [
     *     {
     *       "url": "https://..."
     *     },
     *     {
     *       "url": "https://..."
     *     }
     *   ]
     * }
     *
     * 根据文字描述生成图片，请求参数不需要模型名称
     * image create 模型地址   POST
     */
    public static final String IMAGE_CREATE_MODEL_URL = "https://api.openai.com/v1/images/generations";


    /**
     * Request：
     * curl https://api.openai.com/v1/images/edits \
     *   -H 'Authorization: Bearer YOUR_API_KEY' \
     *   -F image='@otter.png' \
     *   -F mask='@mask.png' \
     *   -F prompt="A cute baby sea otter wearing a beret" \
     *   -F n=2 \
     *   -F size="1024x1024"
     * Response：
     *{
     *   "created": 1589478378,
     *   "data": [
     *     {
     *       "url": "https://..."
     *     },
     *     {
     *       "url": "https://..."
     *     }
     *   ]
     * }
     *
     * 给定两张图片，并且根据文字描述，进行编辑后生成新的图片，请求参数不需要模型名称
     * image edite 模型地址  POST
     */
    public static final String IMAGE_EDITE_MODEL_URL = "https://api.openai.com/v1/images/edits";


    /**
     * curl https://api.openai.com/v1/images/variations \
     *   -H 'Authorization: Bearer YOUR_API_KEY' \
     *   -F image='@otter.png' \
     *   -F n=2 \
     *   -F size="1024x1024"
     *
     *
     *  {
     *   "created": 1589478378,
     *   "data": [
     *     {
     *       "url": "https://..."
     *     },
     *     {
     *       "url": "https://..."
     *     }
     *   ]
     * }
     *
     * 图片变异模型地址 POST
     */
    public static final String IMAGE_VARIATION_MODEL_URL  = "https://api.openai.com/v1/images/variations";


    /**
     * Creates an embedding vector representing the input text.
     *
     */
    public static final String  TEXT_EMBEDDING_001 = "text-embedding-ada-002";
    /**
     *
     *curl https://api.openai.com/v1/embeddings \
     *   -X POST \
     *   -H "Authorization: Bearer YOUR_API_KEY" \
     *   -H "Content-Type: application/json" \
     *   -d '{"input": "The food was delicious and the waiter...",
     *        "model": "text-embedding-ada-002"}'
     *
     * {
     *   "object": "list",
     *   "data": [
     *     {
     *       "object": "embedding",
     *       "embedding": [
     *         0.0023064255,
     *         -0.009327292,
     *         .... (1536 floats total for ada-002)
     *         -0.0028842222,
     *       ],
     *       "index": 0
     *     }
     *   ],
     *   "model": "text-embedding-ada-002",
     *   "usage": {
     *     "prompt_tokens": 8,
     *     "total_tokens": 8
     *   }
     * }
     *
     */
    public static final String EMBEDDING_MODEL_URL = "https://api.openai.com/v1/embeddings";


    /**
     * ID of the model to use. Currently, only gpt-3.5-turbo and gpt-3.5-turbo-0301 are supported.
     * {
     *   "model": "gpt-3.5-turbo",
     *   "messages": [{"role": "user", "content": "Hello!"}]
     * }
     *
     *
     * {
     *   "id": "chatcmpl-123",
     *   "object": "chat.completion",
     *   "created": 1677652288,
     *   "choices": [{
     *     "index": 0,
     *     "message": {
     *       "role": "assistant",
     *       "content": "\n\nHello there, how may I assist you today?",
     *     },
     *     "finish_reason": "stop"
     *   }],
     *   "usage": {
     *     "prompt_tokens": 9,
     *     "completion_tokens": 12,
     *     "total_tokens": 21
     *   }
     * }
     */
    public static final String CHAT_3_5_URL = "https://api.openai.com/v1/chat/completions";

    public static final String CHAT_3_5_TURBO="gpt-3.5-turbo";

    public static final String CHAT_3_5_TURBO_0301="gpt-3.5-turbo-0301";
}
