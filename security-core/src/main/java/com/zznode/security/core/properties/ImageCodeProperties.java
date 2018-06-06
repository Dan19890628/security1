package com.zznode.security.core.properties;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 23:26 2018/3/22
 */
public class ImageCodeProperties extends SmsCodeProperties {
    private int width = 73;
    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
