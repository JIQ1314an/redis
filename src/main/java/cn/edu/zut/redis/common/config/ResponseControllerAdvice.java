package cn.edu.zut.redis.common.config;

import cn.edu.zut.redis.common.entity.ResultVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 将所有返回结果统一进行包装处理
 *
 * @author jiquan
 * @date 2020/11/1 13:47
 */
@RestControllerAdvice(basePackages = {"cn.edu.zut"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return !methodParameter.getParameterType().equals(ResultVO.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVO里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new ResultVO(0,data.toString()));
            } catch (JsonProcessingException e) {
                throw new APIException(102,"返回String类型错误");
            }
        }
        if (returnType.getParameterType().equals(ResultVO.class)) {
            return data;
        }
        // 将原本的数据包装在ResultVO里
        return new ResultVO<>(data);
    }
}
