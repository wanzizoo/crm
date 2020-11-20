package cn.wanzizoo.crm.web.controller;

import cn.wanzizoo.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/19 11:16 上午
 * @description: 登录控制器
 **/
@Controller
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/login")
    public String login(String name, String password, Model model) {
        try {
            //登录成功
            employeeService.login(name, password);
            return "forward:/department/list.do";
        } catch (Exception e) {
            //登录失败
            e.printStackTrace();
            model.addAttribute("errorMsg", e.getMessage());
            return "forward:/login.ftl";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //销毁session
        session.invalidate();
        return "redirect:/login.ftl";

    }

}
