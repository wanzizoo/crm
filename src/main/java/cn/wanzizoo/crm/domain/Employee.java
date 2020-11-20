package cn.wanzizoo.crm.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee extends BaseDomain{

    private String name;

    private String password;

    private String email;

    private Integer age;

    private boolean admin;
    //多对一
    private Department dept;
    //多对多
    private List<Role> roles;

}