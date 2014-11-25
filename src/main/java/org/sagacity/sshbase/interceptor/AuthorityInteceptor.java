/**
 * 
 */
package org.sagacity.sshbase.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author LIZHITAO 系统权限拦截器
 */
public class AuthorityInteceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7087714247878272698L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com
	 * .opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = (HttpServletRequest) invocation
				.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);// 获取request对象
		
		String url = request.getRequestURI();// 获取访问的url地址
		System.out.println(url);

		return invocation.invoke();
	}

}
