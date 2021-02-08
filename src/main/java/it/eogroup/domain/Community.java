package it.eogroup.domain;

import lombok.Data;

@Data
public class Community {
    private Integer communityId;
    private String communityName;
    private String communityDescription;
    private String communityAvatar;
}
