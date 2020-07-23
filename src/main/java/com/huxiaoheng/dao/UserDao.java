package com.huxiaoheng.dao;

import com.huxiaoheng.bean.PageInfo;
import com.huxiaoheng.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
//UserDao.java
public interface UserDao {

    User findByUserName(String username);

    //List<User> findAll();
    List<User> findAll(@Param("start") int start,@Param("username") String username);

    void add(User user);

    void deleteByID(int id);

    User selectByID(int id);

    void update(User user);

    int getTotalCount(@Param("username")String username);


}
