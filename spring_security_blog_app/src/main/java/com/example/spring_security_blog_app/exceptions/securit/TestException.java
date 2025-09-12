package com.example.spring_security_blog_app.exceptions.securit;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class TestException implements AuthenticationFailureHandler {

    String msg;
    public TestException(String msg) {

        this.msg = msg;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("In auth handler msg : " + msg);
        System.out.println("In auth handler getMessage : " + msg);
        response.getWriter().write(exception.getMessage());
    }
}
