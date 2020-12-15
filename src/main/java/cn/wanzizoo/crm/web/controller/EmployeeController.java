package cn.wanzizoo.crm.web.controller;

import cn.wanzizoo.crm.domain.Employee;
import cn.wanzizoo.crm.query.EmployeeQueryObject;
import cn.wanzizoo.crm.query.QueryObject;
import cn.wanzizoo.crm.service.IDepartmentService;
import cn.wanzizoo.crm.service.IEmployeeService;
import cn.wanzizoo.crm.service.IRoleService;
import cn.wanzizoo.crm.util.JsonResult;
import cn.wanzizoo.crm.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/10 11:26 上午
 * @description: 员工控制器
 **/
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IRoleService roleService;


    @RequestMapping("/list")
    @RequiredPermission({"员工列表","employee:list"})
    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo) {
        model.addAttribute("pageInfo", employeeService.query(qo));
        //查询高级查询中所需要的所有部门
        model.addAttribute("depts", departmentService.listAll());
        return "/employee/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiredPermission({"员工删除","employee:delete"})
    public JsonResult delete(Long id, QueryObject qo) {
        JsonResult result = new JsonResult();
        try {
            if (null != id) {
                employeeService.delete(id);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.mark("删除失败");
        }
        return result;
    }

    @RequestMapping("/input")
    @RequiredPermission({"员工编辑","employee:input"})
    public String input(Model model, Long id, Integer currentPage) {
        if (null != id) {
            model.addAttribute("e", employeeService.get(id));
        }
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("depts", departmentService.listAll());
        model.addAttribute("roles", roleService.listAll());
        return "/employee/input";
    }


    @RequestMapping("/saveOrUpdate")
    @RequiredPermission({"员工保存或更新","employee:saveOrUpdate"})
    public String saveOrUpdate(Employee e, Long[] roleIds, Integer currentPage) {
        employeeService.saveOrUpdate(e,roleIds);
        return "redirect:/employee/list.do?currentPage=" + currentPage;
    }


}
