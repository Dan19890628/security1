package com.zznode.security.core.support;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 20:24 2018/3/20
 */
public class SimpleResponse {
    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
