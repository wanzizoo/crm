package cn.wanzizoo.crm.mapper;

import cn.wanzizoo.crm.domain.Role;
import cn.wanzizoo.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    int selectCount(QueryObject qo);

    List<Role> selectList(QueryObject qo);

    void insertRoleAndPermissionRelation(@Param("roleId") Long roleId,@Param("permissionId") Long permissionId);

    void deleteRoleAndPermissionRelation(Long roleId);
}