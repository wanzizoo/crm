package cn.wanzizoo.crm.mapper;

import cn.wanzizoo.crm.domain.Department;
import cn.wanzizoo.crm.query.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);


    List<Department> selectList(QueryObject qo);
}