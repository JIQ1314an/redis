package cn.edu.zut.redis.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jiquan
 * @date 2020/11/2 23:39
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    /**
     * 多个拦截器组成一个拦截器链
     */
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;


    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(authorizationInterceptor)
                .excludePathPatterns("/user/login");
        //拦截所有请求
//                .addPathPatterns("/**");
    }

    /**
     * 跨域支持配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowedHeaders("*")
                //cookie设置
                .allowCredentials(true).maxAge(3600);
    }
}
