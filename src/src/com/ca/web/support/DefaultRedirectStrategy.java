package com.ca.web.support;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultRedirectStrategy implements RedirectStrategy{

	@Override
	public void redirect(final HttpServletRequest request,final HttpServletResponse response, final String redirectUrl)throws IOException {
		response.sendRedirect(redirectUrl);
	}

}
