package cn.wanzizoo.crm.util;

import cn.wanzizoo.crm.domain.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/19 2:55 下午
 * @description: 用户上下文工具
 **/
public class UserContext {

    private static final String EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";

    private static final String EXPRESSIONS_IN_SESSION = "EXPRESSIONS_IN_SESSION";

    public static HttpSession getSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest().getSession();
    }

    public static void setCurrentEmp(Employee employee) {
        getSession().setAttribute(EMPLOYEE_IN_SESSION, employee);
    }

    public static Employee getCurrentEmp() {
        return (Employee) getSession().getAttribute(EMPLOYEE_IN_SESSION);
    }

    public static void setCurrentExpressions(List<String> expressions) {
        getSession().setAttribute(EXPRESSIONS_IN_SESSION, expressions);
    }

    public static List<String> getCurrentExpressions() {
        return (List<String>) getSession().getAttribute(EXPRESSIONS_IN_SESSION);
    }
}
