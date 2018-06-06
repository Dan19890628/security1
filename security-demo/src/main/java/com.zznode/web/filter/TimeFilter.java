package com.zznode.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created by sc_ma on 2018/1/25.
 */
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        Long time = new Date().getTime();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("time filter 耗时：" + (new Date().getTime() - time));
    }

    @Override
    public void destroy() {
        System.out.println("time filter finish");
    }
}
