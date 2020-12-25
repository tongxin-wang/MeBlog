package com.lzw.meblog.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 万能的Cors跨域Filter
 * @author https://springboot.io/t/topic/2107/1
 */
@Component
@WebFilter(urlPatterns = "/*")
@Order(-99999)
public class CorsFilter extends HttpFilter {


    /**
     *
     */
    private static final long serialVersionUID = 2386571986045107652L;
    private static final String OPTIONS_METHOD = "OPTIONS";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String origin = req.getHeader(HttpHeaders.ORIGIN);

        if (!StringUtils.isEmpty(origin)) {
            res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
            res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, x-requested-with, Content-Type, Accept, Authorization");
            res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, OPTIONS, DELETE");
            res.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Cache-Control, Content-Language, Content-Type, Expires, Last-Modified, Pragma");

            if (OPTIONS_METHOD.equalsIgnoreCase(req.getMethod())) {
                res.setStatus(HttpServletResponse.SC_NO_CONTENT);
                res.setContentType(MediaType.TEXT_HTML_VALUE);
                res.setCharacterEncoding("utf-8");
                res.setContentLength(0);
                res.addHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "1800");
                return;
            }
        }

        super.doFilter(req, res, chain);
    }
}