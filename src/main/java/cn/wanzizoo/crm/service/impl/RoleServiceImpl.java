package cn.wanzizoo.crm.service.impl;

import cn.wanzizoo.crm.domain.Role;
import cn.wanzizoo.crm.mapper.EmployeeMapper;
import cn.wanzizoo.crm.mapper.RoleMapper;
import cn.wanzizoo.crm.query.PageResult;
import cn.wanzizoo.crm.query.QueryObject;
import cn.wanzizoo.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/9 6:01 下午
 * @description:
 **/
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void saveOrUpdate(Role role, Long[] permissionIds) {
        if (null == role.getId()) {
            //保存基本角色信息
            roleMapper.insert(role);
        } else {
            roleMapper.updateByPrimaryKey(role);
            //删除旧关系
            roleMapper.deleteRoleAndPermissionRelation(role.getId());

        }


        if (null != permissionIds && permissionIds.length > 0) {
            for (Long permissionId : permissionIds) {
                //保存新关系
                roleMapper.insertRoleAndPermissionRelation(role.getId(),permissionId);
            }
        }

    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteByPrimaryKey(id);
        //删除角色与权限之间的关系
        roleMapper.deleteRoleAndPermissionRelation(id);
        //删除角色与员工之间的关系
        employeeMapper.deleteEmpAndRoleRelation(null,id);
    }

    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.selectAll();
    }

    @Override
    public PageResult<Role> query(QueryObject qo) {
        //查询总条数
        int count = roleMapper.selectCount(qo);
        //总条数为0直接返回空PageResult
        if (0 == count) {
            return new PageResult<Role>(qo.getCurrentPage(), qo.getPageSize());
        }
        //总条数不为0查结果集
        List<Role> roles = roleMapper.selectList(qo);

        //当前业结果集为空查询当前数据最后一页
        if (null == roles || roles.size() == 0) {
            int pageSize = qo.getPageSize();
            if (count % pageSize == 0) {
                qo.setCurrentPage(count / pageSize);
            } else {
                qo.setCurrentPage(count / pageSize + 1);
            }
            roles = roleMapper.selectList(qo);
        }
        //封装返回
        return new PageResult<Role>(roles, qo.getCurrentPage(), qo.getPageSize(), count);

    }
}
