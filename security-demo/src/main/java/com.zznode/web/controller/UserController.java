package com.zznode.web.controller;

import com.zznode.dto.User;
import com.zznode.dto.UserQueryCondition;
import com.zznode.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sc_ma on 2018/1/24.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping(value = "/me")
    public Object me(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @GetMapping
    @ApiOperation("查询用户")
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 1, size = 10, sort = "name,desc") Pageable pageable) {
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

    @GetMapping("/{id}")
    public User getInfo(@PathVariable String id) {
      // throw new UserNotExistException(id,"user not exist");
        System.out.println("进入getInfo方法");
        return new User();
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult bindingResult) {

        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult bindingResult) {
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){

    }

}
