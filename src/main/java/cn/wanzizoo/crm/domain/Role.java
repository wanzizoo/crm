package cn.wanzizoo.crm.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role extends BaseDomain{

    private String name;

    private String sn;

    private List<Permission> permissions;

}