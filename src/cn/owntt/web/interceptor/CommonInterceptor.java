package cn.owntt.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor implements HandlerInterceptor {

	private Logger log = Logger.getLogger(CommonInterceptor.class);

	public CommonInterceptor() {
	}

	/**
	 * Action之前执行,可以进行编码、安全控制等处理
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		log.info("==============执行顺序: 1、preHandle================");
//		System.out.println("sssssssssss");
		return true;
	}

	/**
	 * 生成视图之前执行,有机会修改ModelAndView
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		log.info("==============执行顺序: 2、postHandle================");
	}

	/**
	 * 最后执行，可用于释放资源,可以根据ex是否为null判断是否发生了异常，
	 * 进行日志记录,Object handler是下一个拦截器
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		log.info("==============执行顺序: 3、afterCompletion================");
	}
}
