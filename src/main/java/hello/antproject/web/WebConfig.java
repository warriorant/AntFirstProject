package hello.antproject.web;

import hello.antproject.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",              // 첫 화면
                        "/login",         // 로그인 폼/처리
                        "/logout",        // 로그아웃 (나중에 만들면)
                        "/members/add",   // 회원가입
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/*.ico",
                        "/error"
                );
    }
}
