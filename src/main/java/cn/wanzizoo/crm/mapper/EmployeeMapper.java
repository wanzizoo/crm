package cn.wanzizoo.crm.mapper;

import cn.wanzizoo.crm.domain.Employee;
import cn.wanzizoo.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee employee);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee employee);

    int selectCount(QueryObject qo);

    List<Employee> selectList(QueryObject qo);
    //mapper中方法多参数需要加Param注解来封装
    void insertEmpAndRoleRelation(@Param("empId") Long empId,@Param("roleId") Long roleId);

    void deleteEmpAndRoleRelation(@Param("empId") Long empId,@Param("roleId") Long roleId);

    Employee selectByNameAndPassword(@Param("name") String name, @Param("password") String password);
}