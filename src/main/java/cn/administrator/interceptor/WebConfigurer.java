package cn.administrator.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new UserInterceptor());
        interceptorRegistration.excludePathPatterns("/admin/login.html","/admin/doLogin","/assets/**","/css/*","/js/*",
                "/media/*");
        interceptorRegistration.addPathPatterns("/**");
    }
}
