package cn.wanzizoo.crm.domain;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.io.Serializable;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/19 2:47 下午
 * @description: 通用的实体类
 **/

@Getter
@Setter
public class BaseDomain implements Serializable {
    protected Long id;

}
