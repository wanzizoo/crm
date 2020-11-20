package cn.wanzizoo.crm.web.controller;

import cn.wanzizoo.crm.domain.Department;
import cn.wanzizoo.crm.query.QueryObject;
import cn.wanzizoo.crm.service.IDepartmentService;
import cn.wanzizoo.crm.util.JsonResult;
import cn.wanzizoo.crm.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/9 6:26 下午
 * @description: 部门控制器
 **/
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;


    @RequestMapping("/list")
    @RequiredPermission({"部门列表", "department:list"})
    public String list(Model model, QueryObject qo) {
        model.addAttribute("pageInfo", departmentService.query(qo));
        return "/department/list";
    }

    @RequestMapping("/delete")
    @RequiredPermission({"部门删除", "department:delete"})
    @ResponseBody
    public JsonResult delete(Long id) {
        JsonResult result = new JsonResult();
        try {
            if (null != id) {
                departmentService.delete(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败");
        }
        return result;
    }

    @RequestMapping("/input")
    @RequiredPermission({"部门编辑", "department:input"})
    public String input(Model model, Long id) {
        if (null != id) {
            model.addAttribute("d", departmentService.get(id));
        }
        return "/department/input";
    }

    @RequestMapping("/saveOrUpdate")
    @RequiredPermission({"部门保存或更新", "department:saveOrUpdate"})
    @ResponseBody
    public JsonResult saveOrUpdate(Department d) {
        JsonResult result = new JsonResult();
        try {
            departmentService.saveOrUpdate(d);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除失败");
        }
        return result;
    }


}
