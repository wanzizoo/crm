package cn.wanzizoo.crm.service;

import cn.wanzizoo.crm.domain.Employee;
import cn.wanzizoo.crm.query.PageResult;
import cn.wanzizoo.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/10 5:01 下午
 * @description: 员工的service
 **/
public interface IEmployeeService {
    void saveOrUpdate(Employee employee,Long[] roleIds);

    void delete(Long id);

    Employee get(Long id);

    List<Employee> listAll();

    PageInfo<Employee> query(QueryObject qo);

    void login(String name, String password);
}
