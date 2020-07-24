package com.huxiaoheng.service.impl;

import com.huxiaoheng.bean.Role;
import com.huxiaoheng.bean.UserRole;
import com.huxiaoheng.dao.RoleDao;
import com.huxiaoheng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//注意一定要加上这个@Service
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<Integer> findRoleId(int userId) {
        return roleDao.findRoleIdByUserId(userId);
    }

    @Override
    public List<Role> findRoleByUserId(int userId) {
        return roleDao.findRoleByUserId(userId);
    }

    @Override
    public void add(List<Integer> ids, String userId) {
        for(int roleId:ids){
            UserRole userRole = new UserRole();
            userRole.setUserId(Integer.parseInt(userId));
            userRole.setRoleId(roleId);
            roleDao.addRole(userRole);
        }
    }
}
