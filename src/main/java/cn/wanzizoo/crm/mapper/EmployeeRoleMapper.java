package cn.wanzizoo.crm.mapper;

import cn.wanzizoo.crm.domain.EmployeeRole;
import java.util.List;

public interface EmployeeRoleMapper {
    int insert(EmployeeRole record);

    List<EmployeeRole> selectAll();
}