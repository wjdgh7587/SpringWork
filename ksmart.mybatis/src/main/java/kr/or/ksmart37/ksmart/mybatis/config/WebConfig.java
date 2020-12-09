package kr.or.ksmart37.ksmart.mybatis.config;

import javax.servlet.http.WebConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.or.ksmart37.ksmart.mybatis.interceptor.CommonInterceptor;
import kr.or.ksmart37.ksmart.mybatis.interceptor.LoginInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer {
	



	@Autowired
	private CommonInterceptor commonInterceptor;
	@Autowired
	private LoginInterceptor loginInterceptor;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LoginInterceptor login = new LoginInterceptor();
		String level = login.levelTest();
		System.out.println(level+"2222222222222222222222222222222222222222");
		// 모든 파일 excludePatterns로 css 및 js 를 제외시켰다.
		registry.addInterceptor(commonInterceptor).addPathPatterns("/**").excludePathPatterns("/css/**")
				.excludePathPatterns("/js/**");

		registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/")
				.excludePathPatterns("/login").excludePathPatterns("/logout").excludePathPatterns("/css/**")
				.excludePathPatterns("/js/**");
		

//		if("2".equals(level)) {
//			registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/").excludePathPatterns("/addGoods").excludePathPatterns("/logout").excludePathPatterns("/css/**")
//			.excludePathPatterns("/js/**");	
//		}else if("3".equals(level)) {
//			registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/").excludePathPatterns("/logout").excludePathPatterns("/css/**")
//			.excludePathPatterns("/js/**");	
//		}
		
	}

}
