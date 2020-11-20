package cn.wanzizoo.crm.web.controller;

import cn.wanzizoo.crm.domain.Role;
import cn.wanzizoo.crm.query.QueryObject;
import cn.wanzizoo.crm.service.IPermissionService;
import cn.wanzizoo.crm.service.IRoleService;
import cn.wanzizoo.crm.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/10 11:26 上午
 * @description: 角色控制器
 **/
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    @RequiredPermission({"角色列表", "role:list"})
    public String list(Model model, QueryObject qo) {
        model.addAttribute("result", roleService.query(qo));
        return "/role/list";
    }

    @RequestMapping("/delete")
    @RequiredPermission({"角色删除", "role:delete"})
    public String delete(Long id, QueryObject qo) {
        if (null != id) {
            roleService.delete(id);
        }
        return "redirect:/role/list.do?currentPage=" + qo.getCurrentPage();
    }

    @RequestMapping("/input")
    @RequiredPermission({"角色编辑", "role:input"})
    public String input(Model model, Long id, Integer currentPage) {
        if (null != id) {
            model.addAttribute("r", roleService.get(id));
        }
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("permissions", permissionService.listAll());
        return "/role/input";
    }


    @RequestMapping("/saveOrUpdate")
    @RequiredPermission({"角色保存或更新", "role:saveOrUpdate"})
    public String saveOrUpdate(Role r, Long[] permissionIds, Integer currentPage) {
        roleService.saveOrUpdate(r,permissionIds);
        return "redirect:/role/list.do?currentPage=" + currentPage;
    }


}
