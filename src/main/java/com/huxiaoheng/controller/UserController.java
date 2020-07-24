package com.huxiaoheng.controller;

import com.huxiaoheng.bean.PageInfo;
import com.huxiaoheng.bean.Role;
import com.huxiaoheng.bean.User;
import com.huxiaoheng.service.RoleService;
import com.huxiaoheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

//UserController.java
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/login.do")
    public ModelAndView login(User user,HttpSession session){
        int id = userService.login(user.getUsername(),user.getPassword());

        ModelAndView mv = new ModelAndView();
        if(id!=-1){
            List<Integer> roleIds = roleService.findRoleId(id);
            session.setAttribute("roleIds",roleIds);
            mv.setViewName("main");
            session.setAttribute("user",user);
        }else {
            mv.setViewName("../failer");
        }
        return mv;
    }

//    @RequestMapping("/findAll.do")
//    public  ModelAndView findAll(){
//        List<User> users = userService.findAll();
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("users",users);
//        mv.setViewName("user-list");
//        return mv;
//    }

    @RequestMapping("/findAll.do")
    public  ModelAndView findAll(@RequestParam(defaultValue = "1")int currentPage, String username,
                                 @RequestParam(defaultValue = "0")int type, HttpSession httpSession){
        if(type==1){
            httpSession.setAttribute("searchName",username);
        }else{
            username=(String) httpSession.getAttribute("searchName");
        }
        PageInfo<User> pageInfo = userService.findAll(currentPage,username);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/add.do")
    public String Add(User user){
        System.out.println(user);
        userService.add(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("/deleteById.do")
    public String Delete(int id){
        userService.deleteByID(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("/toUpdate.do")
    public ModelAndView Update(int id){
        User user = userService.selectByID(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user-update");
        mv.addObject("user",user);
        return mv;
    }

    @RequestMapping("/update.do")
    public String Update(User user){
        userService.update(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("/deleteAll.do")
    public String deleteAll(String userList){
        String[] str = userList.split(",");
        List<Integer> ids = new ArrayList<>();
        for (String s:str){
            ids.add(Integer.parseInt(s));
        }
        userService.deleteAll(ids);
        return "redirect:findAll.do";
    }

    @RequestMapping("/toAddRole.do")
    public ModelAndView toAddRole(int id){
        List<Role> roleList = roleService.findRoleByUserId(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("roles",roleList);
        mv.addObject("id",id);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRole.do")
    @ResponseBody
    public String add(String roleList,String userId){
        String[] strs = roleList.split(",");
        List<Integer> ids = new ArrayList<>();
        for(String s:strs){
            ids.add(Integer.parseInt(s));
        }
        roleService.add(ids,userId);
        return "";
    }


}
