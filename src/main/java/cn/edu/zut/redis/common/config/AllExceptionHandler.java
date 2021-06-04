package cn.edu.zut.redis.common.config;

import cn.edu.zut.redis.common.entity.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jiquan
 * @date 2021年05月31日 23:54
 */
@RestControllerAdvice
public class AllExceptionHandler {

    //API响应异常处理
    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        //返回类型是自定义响应体
        return new ResultVO<String>(e.getCode(), e.getMsg());
    }

    //参数检验异常处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return new ResultVO<String>(1002,objectError.getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO<String> APIExceptionHandler(Exception e) {
        e.printStackTrace();
        //返回类型是自定义响应体
        return new ResultVO<String>(500, "系统异常",null);
    }
}
