package cn.wanzizoo.crm.util;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: crm
 * @author: LiuFan
 * @create: 2020/10/23 5:23 下午
 * @description: Json返回类
 **/
@Getter
@Setter
public class JsonResult implements Serializable {
    private boolean success = true;

    private String errorMsg;

    public void mark(String errorMsg) {
        success = false;
        this.errorMsg = errorMsg;
    }
}
