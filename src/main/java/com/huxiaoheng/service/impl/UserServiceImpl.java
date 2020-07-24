package com.huxiaoheng.service.impl;

import com.huxiaoheng.bean.PageInfo;
import com.huxiaoheng.bean.User;
import com.huxiaoheng.dao.UserDao;
import com.huxiaoheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//UserServiceImpl.java
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int login(String username, String password) {
        User user = userDao.findByUserName(username);
        if(user!=null && user.getPassword().equals(password)){
            return user.getId();
        }
        return -1;
    }

    @Override
    public PageInfo<User> findAll(int currentPage, String username) {
        PageInfo<User> pageInfo = new PageInfo<>();
        pageInfo.setSize(5);
        //获取总共的记录条数
        int tc = userDao.getTotalCount(username);
        //获取总页数
        int tp = (int)Math.ceil(tc/5.0);
        pageInfo.setTotalCount(tc);
        //设置页数的范围
        if(currentPage<1){
            pageInfo.setCurrentPage(1);
        }else if(currentPage>tp){
            pageInfo.setCurrentPage(tp);
        }else{
            pageInfo.setCurrentPage(currentPage);
        }
        int start = (pageInfo.getCurrentPage()-1)*5;
        List<User> userList = userDao.findAll(start,username);
        pageInfo.setList(userList);
        return pageInfo;
    }

//    @Override
//    public List<User> findAll() {
//        return userDao.findAll();
//    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void deleteByID(int id) {
        userDao.deleteByID(id);
    }

    @Override
    public User selectByID(int id) {
        return userDao.selectByID(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        userDao.deleteAll(ids);
    }


}
