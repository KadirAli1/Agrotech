package com.agrotech.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TrumsAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint{
    public TrumsAuthenticationEntryPoint() {
        super("/login");
    }

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException e)
            throws IOException, ServletException {

        final String contextPath = request.getContextPath();
        final String requestURI = request.getRequestURI();
        final boolean isApiRequest = requestURI.startsWith(contextPath + "/api");

        if (isApiRequest) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            super.commence(request, response, e);
        }

    }
}
