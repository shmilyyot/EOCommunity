package it.eogroup.domain;

import lombok.Data;

@Data
public class CommentAccountPost extends CommentAccount{
    private String postTitle;
    private Integer communityId;
}
