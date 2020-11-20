package cn.wanzizoo.crm.service;

import cn.wanzizoo.crm.domain.Department;
import cn.wanzizoo.crm.query.PageResult;
import cn.wanzizoo.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/9 5:58 下午
 * @description: 部门的service
 **/
public interface IDepartmentService {
    void saveOrUpdate(Department department);

    void delete(Long id);

    Department get(Long id);

    List<Department> listAll();

    //PageResult<Department> query(QueryObject qo);

    PageInfo<Department> query(QueryObject qo);
}
