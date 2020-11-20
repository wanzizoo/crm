package cn.wanzizoo.crm.domain;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Department extends BaseDomain {
    private String name;

    private String sn;


    public String getJson() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id + "");
        map.put("name", name);
        map.put("sn", sn);
        return JSON.toJSONString(map);
    }

}