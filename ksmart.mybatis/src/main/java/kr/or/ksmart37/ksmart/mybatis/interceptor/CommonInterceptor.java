package kr.or.ksmart37.ksmart.mybatis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CommonInterceptor implements HandlerInterceptor  {
	
	//설정으로 해놓아야 나온다. logger
	private static final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//log.debug("로그레벨 : {}  CommonInterceptor =======================================================", "debug");
		//로그레벨 : {} 중괄호 안에 debug  값이 담겨지게 된다.
		//log.info("로그레벨 : {} CommonInterceptor =======================================================", "info");
		log.info("CommonInterceptor =======================================================");
		log.info("ACCESS INFO ============================================= START");
		log.info("PORT                         ::::::::::::::::::::    {}", request.getLocalPort());
		log.info("SERVER Name                         ::::::::::::::::::::    {}", request.getServerName());
		log.info("HTTP METHOD                         ::::::::::::::::::::    {}", request.getMethod());
		log.info("REQUEST URI                         ::::::::::::::::::::    {}", request.getRequestURI());
		
		log.info("ACCESS INFO ============================================= END");
		//System.out.println("CommonInterceptor ==============================================");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	//Reqeust 끝나고 나서
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	
		log.info("CommonInterceptor ==============================================");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	//View와 관련된 모든 작업
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		log.info("CommonInterceptor ==============================================");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
