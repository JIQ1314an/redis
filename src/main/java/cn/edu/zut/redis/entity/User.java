package cn.edu.zut.redis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author jiquan
 * @Date 2021/5/31
 * @TIME 10:38
 */
@Getter
@Setter
@ToString
public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String age;
    private String gender;
}
