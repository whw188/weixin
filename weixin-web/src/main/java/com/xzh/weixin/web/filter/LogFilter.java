package com.xzh.weixin.web.filter;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@WebFilter
public class LogFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        StringBuilder stringBuilder = new StringBuilder(req.getRequestURI()).append(" ");
        try {
            Map<String, String[]> parameterMap = req.getParameterMap();
            String params = JSON.toJSONString(parameterMap);

            stringBuilder.append(params);
            logger.info(stringBuilder.toString());
            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.error(stringBuilder.toString(), e);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
