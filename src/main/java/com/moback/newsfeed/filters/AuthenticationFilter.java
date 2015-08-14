/*package com.moback.newsfeed.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.moback.newsfeed.Constants;
import com.moback.newsfeed.auth.AuthToken;
import com.moback.newsfeed.auth.AuthorizationService;

//@WebFilter(urlPatterns = "/OAuth/*")
public class AuthenticationFilter implements Filter {
	
	@Autowired
	private AuthorizationService authorizationService;

	@Override
	public void destroy() {
		;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		switch(request.getServletPath()) {
			case "/newsfeed/login" : 
			case "/newsfeed/user" :
			case "/newsfeed/user_post" : chain.doFilter(request, response);
									        return;						        
			default : String authTokenString = request.getHeader(Constants.AUTH_TOKEN_HTTP_HEADER);
					  System.out.println("authToken : " + authTokenString);
					  AuthToken authToken = authorizationService.assertUser(authTokenString);
					  if(authToken == null) {
						  response.sendError(401, "Authentication Failed");
					  }
					  else {
						  request.setAttribute("userId", authToken.getUserId());
						  chain.doFilter(request, response);
					  } 
					  System.out.println("Auth Filter");
					  chain.doFilter(request, response);
					  return;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		;
	}

}*/
