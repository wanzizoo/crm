package cn.wanzizoo.crm.mapper;

import cn.wanzizoo.crm.domain.Permission;
import cn.wanzizoo.crm.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    List<Permission> selectAll();

    int selectCount(QueryObject qo);

    List<Permission> selectList(QueryObject qo);

    List<String> selectAllExpression();

    List<String> selectExpressionByEmpId(Long empId);
}