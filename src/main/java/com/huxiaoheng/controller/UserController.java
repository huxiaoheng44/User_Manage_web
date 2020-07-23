package com.huxiaoheng.controller;

import com.huxiaoheng.bean.PageInfo;
import com.huxiaoheng.bean.User;
import com.huxiaoheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

//UserController.java
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    public ModelAndView login(User user){
        int id = userService.login(user.getUsername(),user.getPassword());
        ModelAndView mv = new ModelAndView();
        if(id!=-1){
            mv.setViewName("main");
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



}
