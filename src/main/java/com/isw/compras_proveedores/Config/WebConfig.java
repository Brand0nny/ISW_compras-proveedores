package com.isw.compras_proveedores.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.isw.compras_proveedores.Config.Session.SessionInterceptor;
@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/login","/register","/ordenes/aprobar/**","/ordenes/rechazar/**","/ordenes/enviada/**","/finanzas/**");
    }
    @Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("http://localhost:3000")
      .allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
      .allowedHeaders("*")           
      .exposedHeaders("Set-Cookie") 
      .allowCredentials(true)
      .maxAge(3600);
}


}