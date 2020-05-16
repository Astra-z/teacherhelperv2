package com.spm.teacherhelperv2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * description: CORSConfig
 * date: 2020/5/16 10:21
 * author: Zhangjr
 * version: 1.0
 */
//@Configuration
//public class CORSConfig implements WebMvcConfigurer {
//
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**")
////                .allowedOrigins("*")
////                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
////                .allowCredentials(true)
////                .maxAge(3600)
////                .allowedHeaders("*");
////    }
//
//    private CorsConfiguration corsConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setMaxAge(3600L);
//        return corsConfiguration;
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig());
//        return new CorsFilter(source);
//    }
//
//}
