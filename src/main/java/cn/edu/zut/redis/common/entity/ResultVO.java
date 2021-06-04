package cn.edu.zut.redis.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jiquan
 * @date 2021年05月31日 23:57
 */
@Data
public class ResultVO<T> implements Serializable {

    /**
     * 状态码，比如100代表响应成功
     */
    private int code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    public ResultVO(T data) {
        this(0,"success", data);
    }

    public ResultVO(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResultVO(int code,String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO() {

    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public static <T> ResultVO<T> SUCCESS(String msg,T data){
        return new ResultVO(0,msg,data);
    }
    public static <T> ResultVO<T> SUCCESS(T data){
        return new ResultVO(data);
    }

    public static <T> ResultVO<T> ERROR(int code,String msg,T data){
        return new ResultVO(code,msg,data);
    }
}
