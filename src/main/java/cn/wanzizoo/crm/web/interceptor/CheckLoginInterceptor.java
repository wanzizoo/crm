package cn.wanzizoo.crm.web.interceptor;

import cn.wanzizoo.crm.domain.Employee;
import cn.wanzizoo.crm.util.UserContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/19 12:01 下午
 * @description: 登录校验拦截器
 **/
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee currentEmp = UserContext.getCurrentEmp();
        if (null == currentEmp) {
            response.sendRedirect("/login.ftl");
            return false;
        }
        return true;
    }
}
