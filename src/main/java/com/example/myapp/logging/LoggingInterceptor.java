package com.example.myapp.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(">>>>>>>>>>>> >>>>>>>>>>>> >>>>>>>>>>>> >>>>>>>>>>>>");
        log.info(">>> Request : {}, {}", request.getMethod(), request.getRequestURI());

        // Log Headers
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            log.info("Header: {} = {}", headerName, request.getHeader(headerName));
            // mask sensitive data if required
        }

        // Log Request Body (if applicable)
        if ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod())) {
            log.info("Request Body: {}", getRequestBody(request));
            // mask sensitive data if required
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("<<< Response : {}, {}, Status - {}", request.getMethod(), request.getRequestURI(), response.getStatus());
        log.info("<<<<<<<<<<<< <<<<<<<<<<<< <<<<<<<<<<<< <<<<<<<<<<<<");
    }

    private String getRequestBody(HttpServletRequest request) {
        if (!(request instanceof ContentCachingRequestWrapper)) {
            return "Request body not available";
        }

        ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) request;
        byte[] content = wrapper.getContentAsByteArray();

        if (content.length == 0) {
            return "Request body is empty";
        }

        return new String(content, StandardCharsets.UTF_8);
    }
}
