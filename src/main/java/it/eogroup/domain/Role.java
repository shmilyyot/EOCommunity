package it.eogroup.domain;

import lombok.Data;

@Data
public class Role {
    private Integer accountId;
    private String accountRole;
    private String roleName;
}
