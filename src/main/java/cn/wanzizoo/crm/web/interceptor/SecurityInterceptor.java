package cn.wanzizoo.crm.web.interceptor;

import cn.wanzizoo.crm.domain.Employee;
import cn.wanzizoo.crm.util.RequiredPermission;
import cn.wanzizoo.crm.util.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/19 12:01 下午
 * @description: 权限校验拦截器
 **/
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee employee = UserContext.getCurrentEmp();
        //判断当前用户是否为管理员，是则放行
        if (employee.isAdmin()) {
            return true;
        }
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        //判断当前访问的方法是否需要权限校验，否则放行
        if (!method.isAnnotationPresent(RequiredPermission.class)) {
            return true;
        }
        RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
        //获取当前方法的权限表达式
        String expression = annotation.value()[1];
        //获取当前用户的所有权限表达式
        List<String> expressions = UserContext.getCurrentExpressions();
        //判断当前用户是否拥有此方法的权限
        if (expressions.contains(expression)) {
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.ftl").forward(request, response);
        //拦截
        return false;
    }
}
