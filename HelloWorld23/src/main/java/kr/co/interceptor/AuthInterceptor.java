package kr.co.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();//세션 정보를 가져옴
		
		if(session.getAttribute("login")==null) {
			response.sendRedirect("/user/login");
			return false;
		}
		return true;
	}
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session=request.getSession();
		ModelMap map = modelAndView.getModelMap();
		Object userVO=map.get("userVO");
		if(userVO !=null) {
			session.setAttribute("login", userVO);
			response.sendRedirect("/");
		}else {
			
		}
	}
	
}
