package cn.owntt.filter;


import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 编码过滤器
 * @author LJ
 *
 */
public class charsetFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest request=(HttpServletRequest) servletRequest;
			HttpServletResponse response=(HttpServletResponse) servletResponse;
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
//			response.setContentType("text/html;charset=utf-8");
			 
//			List list=(List)request.getSession().getAttribute("admin");
//			if(list==null||list.equals("")){
//				HttpSession session=request.getSession();
//				session.removeAttribute("admin");
//				session.invalidate();
//				session=null;
//				//response.sendRedirect(request.getContextPath()+"/login.jsp");
//				response.getWriter().print("<script>window.parent.window.location.href='"+request.getContextPath()+"/login.jsp"+"'</script>");
//			}else 
				chain.doFilter(request, response);
	}
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
