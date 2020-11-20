package cn.wanzizoo.crm.service.impl;

import cn.wanzizoo.crm.domain.Permission;
import cn.wanzizoo.crm.mapper.PermissionMapper;
import cn.wanzizoo.crm.query.PageResult;
import cn.wanzizoo.crm.query.QueryObject;
import cn.wanzizoo.crm.service.IPermissionService;
import cn.wanzizoo.crm.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/15 6:05 下午
 * @description:
 **/
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Permission> listAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public PageResult<Permission> query(QueryObject qo) {
        //查询总条数
        int count = permissionMapper.selectCount(qo);
        //总条数为0直接返回空PageResult
        if (0 == count) {
            return new PageResult<Permission>(qo.getCurrentPage(), qo.getPageSize());
        }
        //总条数不为0查结果集
        List<Permission> permissions = permissionMapper.selectList(qo);

        //当前业结果集为空查询当前数据最后一页
        if (null == permissions || permissions.size() == 0) {
            int pageSize = qo.getPageSize();
            if (count % pageSize == 0) {
                qo.setCurrentPage(count / pageSize);
            } else {
                qo.setCurrentPage(count / pageSize + 1);
            }
            permissions = permissionMapper.selectList(qo);
        }
        //封装返回
        return new PageResult<Permission>(permissions, qo.getCurrentPage(), qo.getPageSize(), count);

    }


    //1:获取spring容器
    @Autowired
    private ApplicationContext ctx;

    @Override
    public void reload() {
        //获取所有权限表达式
        List<String> expressions = permissionMapper.selectAllExpression();

        //2:从容器中获取所有controller,根据类上的注解获取所有Controller
        // key：bean的名称       value：Controller对象
        Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> collections = beansWithAnnotation.values();
        //3:获取每个controller中的方法
        for (Object collection : collections) {
            //获取当前Controller对象的所有方法，不包括私有的
            Method[] declaredMethods = collection.getClass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                //4:获取方法上贴的注解,判断方法上是否有指定的注解
                if (method.isAnnotationPresent(RequiredPermission.class)) {
                    //5:获取注解中传递的参数
                    RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
                    String[] value = annotation.value();

                    //6:将参数保存在数据库中
                    if (null != value && value.length > 0) {
                        Permission permission = new Permission(value[0], value[1]);
                        //该权限表达式数据库中不存在，才执行保存操作
                        if (!expressions.contains(permission.getExpression())) {
                            permissionMapper.insert(permission);
                        }
                    }
                }
            }
        }

    }
}
