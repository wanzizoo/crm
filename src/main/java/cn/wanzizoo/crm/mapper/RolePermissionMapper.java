package cn.wanzizoo.crm.mapper;

import cn.wanzizoo.crm.domain.RolePermission;
import java.util.List;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    List<RolePermission> selectAll();
}