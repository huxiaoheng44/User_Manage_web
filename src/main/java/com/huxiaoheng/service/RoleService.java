package com.huxiaoheng.service;

import com.huxiaoheng.bean.Role;

import java.util.List;

public interface RoleService {

    List<Integer> findRoleId(int userId);

    List<Role> findRoleByUserId(int userId);

    void add(List<Integer> ids,String userId);
}
