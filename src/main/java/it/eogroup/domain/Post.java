package it.eogroup.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Post {
    private Integer postId;
    private Integer communityId;
    private Integer accountId;
    private String postTitle;
    private LocalDateTime postTime;
}
