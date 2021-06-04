package cn.edu.zut.redis.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author jiquan
 * @Date 2021/6/1
 * @TIME 21:33
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    /**
     * 进入controller层之前拦截请求
     *
     * @param
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        Object token = session.getAttribute("TOKEN");
        System.out.println(token);
        if (token == null) throw new Exception("登录授权失败");
        return true;


    }

    //访问controller之后 访问视图之前被调用
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //访问视图之后被调用
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

