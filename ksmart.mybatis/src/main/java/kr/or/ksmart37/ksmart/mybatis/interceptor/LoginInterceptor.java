package kr.or.ksmart37.ksmart.mybatis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	private String level = null;
	public String levelTest() {
		return level;
	}
	public void setLevleTest() {
		 
	}
	
	
	private static final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);
	
	//만약 회원이 아닌 누군가가 설정하지 않은 곳으로 하게 되면 문제가 발생하게 된다. 이를 아래와 같이 방지가 가능하다.
	
	//Request 하기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		HttpSession session = request.getSession();
		//getSession의 데이터 타입은 Object 이다.
		String sessionId  =(String) session.getAttribute("SID");
		String sessionLevel  =(String) session.getAttribute("SLEVEL");
		String requestUri = request.getRequestURI();
		
		if(sessionId == null) {
			if(requestUri.indexOf("/addMember")>-1) {
				return true;
			}
			response.sendRedirect("/login");
			return false;
			//계속해서 돌리면, 뻗어버리기에 이를 방지하기위해 false 삽입
		}else {
			if(sessionLevel != null && "2".equals(sessionLevel)) {
				if(requestUri.indexOf("/memberList") > -1 || requestUri.indexOf("/addMember") > -1 
						|| requestUri.indexOf("/sellerList") >- 1 || requestUri.indexOf("/modifyMember") > -1
						|| requestUri.indexOf("/removeMember") > -1){
					response.sendRedirect("/");
					return false;
				}
				level = sessionLevel;
				//response.sendRedirect("/");
				//return false;
			}
			if(sessionLevel != null && "3".equals(sessionLevel)) {
				if(requestUri.indexOf("/memberList") > -1 || requestUri.indexOf("/addMember") > -1 
						|| requestUri.indexOf("/sellerList") >- 1 || requestUri.indexOf("/modifyMember") > -1
						|| requestUri.indexOf("/removeMember") > -1 || requestUri.indexOf("/addGoods") >-1
						|| requestUri.indexOf("/modifyGoods") > -1 || requestUri.indexOf("/removeGoods") >-1){
					response.sendRedirect("/");
					return false;
				}
				level = sessionLevel;
				System.out.println(level);
				//response.sendRedirect("/");
				//return false;
			}
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	//Request 요청 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
