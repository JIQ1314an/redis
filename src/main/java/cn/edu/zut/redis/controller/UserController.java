package cn.edu.zut.redis.controller;

import cn.edu.zut.redis.entity.User;
import cn.edu.zut.redis.service.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Author jiquan
 * @Date 2021/5/31
 * @TIME 10:36
 */
@RestController
public class UserController {

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/login")
    public Object login(String auth, int accessTime, HttpSession session) {
        if ("123456".equals(auth)) {
            String tk = UUID.randomUUID().toString();
            session.setAttribute("TOKEN", tk);
            session.setMaxInactiveInterval(accessTime); //访问时间10s
            return tk;
        }

        return false;
    }

    @RequestMapping("/user/add")
    public Object add(String key, User user) {
        System.out.println(key);
        System.out.println(user);
        return redisUtil.set(key, user);
    }

    @RequestMapping("/user/get")
    public Object get(String key) {
        return redisUtil.get(key);
    }

}
