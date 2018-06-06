package com.zznode.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;


/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 11:08 2018/2/14
 */
@RestController
@RequestMapping("/order")
public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Queue queue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping
    public DeferredResult<String> order() throws Exception {
        logger.info("主线程开始");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        queue.setPlaceOrder(orderNumber);
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
        /*Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程返回");
                return "success";
            }
        };*/
        logger.info("主线程返回");
        return result;
    }

}
