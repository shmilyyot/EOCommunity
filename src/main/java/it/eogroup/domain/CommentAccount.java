package it.eogroup.domain;

import lombok.Data;

@Data
public class CommentAccount extends Comment{
    private Integer accountId;
    private String accountName;
    private String accountAvatar;
}
