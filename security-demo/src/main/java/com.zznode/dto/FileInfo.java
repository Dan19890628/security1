package com.zznode.dto;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 21:38 2018/2/7
 */
public class FileInfo {
    public FileInfo(String path) {
        this.path = path;
    }

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
