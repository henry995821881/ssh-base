/**
 * 
 */
package org.sagacity.core.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Admin
 * 
 */
public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	private HttpServletRequest request;
	private HttpServletResponse response;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1096445793428612291L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletResponseAware#setServletResponse
	 * (javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 获取request
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * 获取response
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * 获取session
	 * @return
	 */
	public HttpSession getSession() {
		return request.getSession();
	}

	/**
	 * 获得servlet上下文
	 * 
	 * @return
	 */
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public String getRealPath(String path) {
		return getServletContext().getRealPath(path);
	}
}
