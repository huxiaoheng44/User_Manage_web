package com.huxiaoheng.service;

import com.huxiaoheng.bean.PageInfo;
import com.huxiaoheng.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
//UserService.java
public interface UserService {
    int login(String username,String password);

//    List<User> findAll();
    PageInfo<User> findAll(int currentPage,String username);

    void add(User user);

    void deleteByID(int id);

    User selectByID(int id);

    void update(User user);

    void deleteAll(@Param("ids")List<Integer> ids);
}
