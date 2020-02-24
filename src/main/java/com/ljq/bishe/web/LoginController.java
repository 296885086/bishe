package com.ljq.bishe.web;

import com.ljq.bishe.pojo.Logininfo;
import com.ljq.bishe.pojo.Student;
import com.ljq.bishe.pojo.Teacher;
import com.ljq.bishe.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;
    /*
    * 用于用户登录
    * 传递参数Logininfo
    * 返回ModelAndView
    * */
    @PostMapping("/submit")
    public ModelAndView login(@ModelAttribute Logininfo logininfo, Model model) {
        String logintype = logininfo.getLogintype();
        String username = logininfo.getUsername();
        String password = logininfo.getPassword();
        String teacher = "teacher";
        String student = "student";
        ModelAndView modelAndView = null;
        Logininfo logininfo1 = new Logininfo();
        logininfo1.setMsg("用户名密码错误！！！");
        logininfo1.setPassword(password);
        logininfo1.setUsername(username);

        if (logintype.equals(student)) {
            Student stulogin = loginService.getstu(username);
            if (stulogin != null && stulogin.getStupassword().equals(password)) {
                model.addAttribute("stuinfo", stulogin);
                modelAndView = new ModelAndView("student/studentMain", "userModel", model);
            } else {
                logininfo1.setLogintype(student);
                model.addAttribute("logininfo", logininfo1);
                modelAndView = new ModelAndView("login", "userModel", model);
            }
        } else if (logintype.equals(teacher)) {
            Teacher tealogin = loginService.gettea(username);
            if (tealogin != null && tealogin.getTeapassword().equals(password)) {
                model.addAttribute("teainfo", tealogin);
                modelAndView = new ModelAndView("teacher/teacherMain", "userModel", model);
            } else {
                logininfo1.setLogintype(teacher);
                model.addAttribute("logininfo", logininfo1);
                modelAndView = new ModelAndView("login", "userModel", model);
            }
        }
        return modelAndView;
    }

    /*
    * 用户登录页面
    * */
    @GetMapping
    public ModelAndView login(Model model) {
        Logininfo logininfo = new Logininfo();
        model.addAttribute("logininfo", logininfo);
        ModelAndView mv = new ModelAndView("login", "userModel", model);
        return mv;
    }

    /*页面跳转 部分*/
    @GetMapping("/studentMain")
    public ModelAndView listHero(@PathVariable("user") String user, Model model) throws Exception {
        model.addAttribute("username", user);
        ModelAndView mv = new ModelAndView("student/studentMain", "userModel", model);
        return mv;
    }

    /*
    * 登录成功跳转页面*/
    @GetMapping("/fragment")
    public ModelAndView fragment(Model model) {
        ModelAndView mv = new ModelAndView("teacher/uploadHomework");
        return mv;
    }

}