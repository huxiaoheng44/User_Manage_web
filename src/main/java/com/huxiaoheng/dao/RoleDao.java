package com.huxiaoheng.dao;

import com.huxiaoheng.bean.Role;
import com.huxiaoheng.bean.UserRole;

import java.util.List;

public interface RoleDao {

    List<Integer> findRoleIdByUserId(int userId);

    List<Role> findRoleByUserId(int id);

    void addRole(UserRole userRole);

}
