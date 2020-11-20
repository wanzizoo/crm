package cn.wanzizoo.crm.web.controller;

import cn.wanzizoo.crm.query.QueryObject;
import cn.wanzizoo.crm.service.IPermissionService;
import cn.wanzizoo.crm.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/15 6:26 下午
 * @description: 权限控制器
 **/
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService  permissionService;


    @RequestMapping("/list")
    @RequiredPermission({"权限列表","permission:list"})
    public String list(Model model, QueryObject qo) {
        model.addAttribute("result", permissionService.query(qo));
        return "/permission/list";
    }

    @RequestMapping("/delete")
    @RequiredPermission({"权限删除","permission:delete"})
    public String delete(Long id, QueryObject qo) {
        if (null != id) {
            permissionService.delete(id);
        }
        return "redirect:/permission/list.do?currentPage=" + qo.getCurrentPage();
    }

    @RequestMapping("/reload")
    @RequiredPermission({"权限加载","permission:reload"})
    public String reload() {
        permissionService.reload();
        return "redirect:/permission/list.do";
    }
}
