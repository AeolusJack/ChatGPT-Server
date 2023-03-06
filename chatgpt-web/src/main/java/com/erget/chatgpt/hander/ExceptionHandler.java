package org.xxx.ac.zpk.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONUtil;
import com.erget.chatgpt.dto.ResultDtoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理
 * @author xxx
 *
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        ModelAndView mv = new ModelAndView();
        /* 使用response返回 */
        response.setStatus(HttpStatus.OK.value()); // 设置状态码
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
        response.setCharacterEncoding("UTF-8"); // 避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
              //调用自定义方法，得到异常处理结果
            response.getWriter().write(JSONUtil.toJsonStr(ResultDtoFactory.toNack(ex.getMessage())));
        } catch (IOException e) {
            logger.error("异常:" + e.getMessage(), e);
        }

        logger.debug("异常:" + ex.getMessage(), ex);
        return mv;
    }


}
