package cn.wanzizoo.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/10 6:03 下午
 * @description: 员工高级查询对象
 **/
@Getter
@Setter
public class EmployeeQueryObject extends QueryObject {
    private String keyword;

    private Long deptId = -1L;

    public String getKeyword() {
        return null == keyword || keyword.trim().equals("") ? null : keyword;
    }
}
