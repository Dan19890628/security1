package com.zznode.browser.security.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 10:37 2018/5/15
 */
public class ZznodeSessionExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        sessionInformationExpiredEvent.getResponse().setContentType("application/json;charset=UTF-8");
        sessionInformationExpiredEvent.getResponse().getWriter().write("并发登录");
    }
}
