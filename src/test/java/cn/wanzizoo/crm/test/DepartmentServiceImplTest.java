package cn.wanzizoo.crm.test;

import cn.wanzizoo.crm.domain.Department;
import cn.wanzizoo.crm.query.PageResult;
import cn.wanzizoo.crm.query.QueryObject;
import cn.wanzizoo.crm.service.IDepartmentService;
import cn.wanzizoo.crm.util.JsonResult;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceImplTest {
    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void saveOrUpdate() {
        Department department = new Department("测试","11");
        departmentService.saveOrUpdate(department);

    }

    @Test
    public void delete() {
        departmentService.delete(7L);
    }

    @Test
    public void get() {
        /*Department department = departmentService.get(2L);
        System.out.println(departmentService.getClass());
        System.out.println(department.getJson());*/
        JsonResult jsonResult = new JsonResult();
        System.out.println(jsonResult.toString());
    }

    @Test
    public void listAll() {
        List<Department> departments = departmentService.listAll();
        System.out.println(departments.toString());
    }

    @Test
    public void query() {
        PageInfo<Department> query = departmentService.query(new QueryObject());
        System.out.println(JSON.toJSONString(query));
    }

}