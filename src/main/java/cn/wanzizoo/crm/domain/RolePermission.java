package cn.wanzizoo.crm.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RolePermission {
    private Long role_id;

    private Long permission_id;
}