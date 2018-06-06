package com.zznode.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 12:48 2018/2/14
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Queue queue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(() -> {
            while (true) {
                if (StringUtils.isNotBlank(queue.getCompleteOrder())) {
                    String orderNumber = queue.getCompleteOrder();
                    logger.info("返回订单处理结果：" + orderNumber);
                    deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
                    queue.setCompleteOrder(null);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }
}
