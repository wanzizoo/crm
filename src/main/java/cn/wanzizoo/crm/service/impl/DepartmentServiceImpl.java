package cn.wanzizoo.crm.service.impl;

import cn.wanzizoo.crm.domain.Department;
import cn.wanzizoo.crm.mapper.DepartmentMapper;
import cn.wanzizoo.crm.query.PageResult;
import cn.wanzizoo.crm.query.QueryObject;
import cn.wanzizoo.crm.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void saveOrUpdate(Department department) {
        if (null == department.getId()) {
            departmentMapper.insert(department);
        } else {
            departmentMapper.updateByPrimaryKey(department);
        }

    }

    @Override
    public void delete(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Department get(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> listAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public PageInfo<Department> query(QueryObject qo) {

        //告诉分页插件此条代码下的第一条查询，执行拦截拼接分页操作
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Department> departments = departmentMapper.selectList(qo);

        return new PageInfo<>(departments);
    }
}
