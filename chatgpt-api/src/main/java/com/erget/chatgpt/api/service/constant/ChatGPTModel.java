package com.erget.chatgpt.api.service.constant;
/**
 * ChatGPT的模型
 */
public final class ChatGPTModel {

    private ChatGPTModel() {

    }

    /**
     * chatgpt api-key
     */
    public static final String API_KEY = "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";
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
}
