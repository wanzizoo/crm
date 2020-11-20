package cn.wanzizoo.crm.service;

import cn.wanzizoo.crm.domain.Permission;
import cn.wanzizoo.crm.query.PageResult;
import cn.wanzizoo.crm.query.QueryObject;

import java.util.List;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/15 5:58 下午
 * @description: 权限的service
 **/
public interface IPermissionService {

    void delete(Long id);

    List<Permission> listAll();

    PageResult<Permission> query(QueryObject qo);

    void reload();

}
