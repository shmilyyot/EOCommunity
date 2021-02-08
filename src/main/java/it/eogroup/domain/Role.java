package it.eogroup.domain;

import lombok.Data;

/*角色*/
@Data
public class Role {
    private Integer accountId;
    private String accountRole;
    private String roleName;
}
