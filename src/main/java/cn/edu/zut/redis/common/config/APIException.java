package cn.edu.zut.redis.common.config;

/**
 * 自定义API接口异常
 *
 * @author jiquan
 * @date 2020/11/1 13:51
 */
public class APIException extends RuntimeException{

    private int code;
    private String msg;

    public APIException() {
        this(1001,"failed");
    }

    public APIException(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
