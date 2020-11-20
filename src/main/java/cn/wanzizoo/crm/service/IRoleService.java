package cn.wanzizoo.crm.service;

import cn.wanzizoo.crm.domain.Role;
import cn.wanzizoo.crm.query.PageResult;
import cn.wanzizoo.crm.query.QueryObject;

import java.util.List;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/9 5:58 下午
 * @description: 部门的service
 **/
public interface IRoleService {
    void saveOrUpdate(Role role, Long[] permissionIds);

    void delete(Long id);

    Role get(Long id);

    List<Role> listAll();

    PageResult<Role> query(QueryObject qo);
}
