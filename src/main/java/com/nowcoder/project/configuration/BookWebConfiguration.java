package com.nowcoder.project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nowcoder.project.interceptor.HostInfoInterceptor;
import com.nowcoder.project.interceptor.LoginInterceptor;

@Component
public class BookWebConfiguration implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Autowired
	private HostInfoInterceptor hostInfoInterceptor;

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(hostInfoInterceptor).addPathPatterns("/**");
				registry.addInterceptor(loginInterceptor).addPathPatterns("/books/**");
			}
		};

	}
}