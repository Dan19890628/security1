package com.zznode.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 22:27 2018/3/27
 */
public class ValidateCode implements Serializable {
    private String code;
    private LocalDateTime expireTime;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expireTime);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
