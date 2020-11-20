package cn.wanzizoo.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/9 7:00 下午
 * @description: 通用查询对象
 **/
@Getter
@Setter
public class QueryObject {
    private int currentPage = 1;

    private int pageSize = 5;

    public int getStart() {
        return (currentPage - 1) * pageSize;
    }
}
