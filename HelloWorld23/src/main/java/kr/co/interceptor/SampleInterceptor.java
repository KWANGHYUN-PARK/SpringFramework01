package kr.co.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter{

	/*
	 * ~adapter라고 끝나는 클래스 단어가 나오면 중괄호 안에 아무것도 없는
	 */
	
	
	/* 우클릭 -> Source -> get... */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("prehandle==============================");
		
		HandlerMethod hMethod=(HandlerMethod) handler;
		Method method=hMethod.getMethod();
		System.out.println(method);
		
		Object bean =hMethod.getBean();
		System.out.println(bean);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("posthandle========================");
		modelAndView.setViewName("home");
		modelAndView.addObject("serverTime", "ok");
	}
	
	
	
	

}
