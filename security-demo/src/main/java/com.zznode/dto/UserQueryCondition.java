package com.zznode.dto;

/**
 * Created by sc_ma on 2018/1/24.
 */
public class UserQueryCondition {
    private String username;
    private String age;
    private String ageTo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(String ageTo) {
        this.ageTo = ageTo;
    }
}
