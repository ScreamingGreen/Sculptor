package com.screaminggreen.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.screaminggreen.beans.SessionBean;

public class LoggedInFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest sreq, ServletResponse sresp,
			FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sreq;        
        
        SessionBean sBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        if(sBean == null) {
        	HttpServletResponse response = (HttpServletResponse) sresp;
        	response.sendRedirect("/loginpage.jsp?nouser=true&error=true");
        	return;
        }
        
        arg2.doFilter(sreq, sresp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}