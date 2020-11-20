package cn.wanzizoo.crm.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Permission extends BaseDomain{

    private String name;

    private String expression;

    public Permission(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }

}