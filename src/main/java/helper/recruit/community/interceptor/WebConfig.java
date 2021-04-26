package helper.recruit.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private  SessionInterceptor sessionInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        // for every path, add interceptor
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
    }
}
