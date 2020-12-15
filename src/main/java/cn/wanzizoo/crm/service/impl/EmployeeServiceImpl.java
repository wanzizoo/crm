package cn.wanzizoo.crm.service.impl;

import cn.wanzizoo.crm.domain.Department;
import cn.wanzizoo.crm.domain.Employee;
import cn.wanzizoo.crm.mapper.EmployeeMapper;
import cn.wanzizoo.crm.mapper.PermissionMapper;
import cn.wanzizoo.crm.query.PageResult;
import cn.wanzizoo.crm.query.QueryObject;
import cn.wanzizoo.crm.service.IEmployeeService;
import cn.wanzizoo.crm.util.LogicException;
import cn.wanzizoo.crm.util.UserContext;
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
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void saveOrUpdate(Employee employee, Long[] roleIds) {
        if (null == employee.getId()) {
            //保存员工基本信息
            employeeMapper.insert(employee);
        } else {
            employeeMapper.updateByPrimaryKey(employee);
            //删除旧关系
            employeeMapper.deleteEmpAndRoleRelation(employee.getId(), null);
        }

        //保存员工和角色的关系
        if (null != roleIds && roleIds.length > 0) {
            for (Long roleId : roleIds) {
                employeeMapper.insertEmpAndRoleRelation(employee.getId(), roleId);
            }
        }

    }

    @Override
    public void delete(Long id) {
        //删除员工
        employeeMapper.deleteByPrimaryKey(id);
        //删除关系
        employeeMapper.deleteEmpAndRoleRelation(id, null);
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> listAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public PageInfo<Employee> query(QueryObject qo) {

        //告诉分页插件此条代码下的第一条查询，执行拦截拼接分页操作
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Employee> employees = employeeMapper.selectList(qo);

        return new PageInfo<>(employees);
    }

    @Override
    public void login(String name, String password) {
        Employee emp = employeeMapper.selectByNameAndPassword(name, password);
        if (null == emp) {
            //失败
            throw new LogicException("账号或密码错误");
        }
        //成功，不能直接使用表现层的api，间接使用，将其封装到工具类中，再使用
        //ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //不要接收，直接使用，不然导入了包，产生了依赖,就不是间接使用
        //HttpSession session = requestAttributes.getRequest().getSession();
        //共享当前用户
        UserContext.setCurrentEmp(emp);
        //查询当前用户拥有的权限表达式
        List<String> expressions = permissionMapper.selectExpressionByEmpId(emp.getId());
        //共享当前用户拥有到权限表达式
        UserContext.setCurrentExpressions(expressions);

    }
}
