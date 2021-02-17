package it.eogroup.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Comment {
    private Integer commentId;
    private Integer postId;
    private Integer accountId;
    private LocalDateTime commentTime;
    private String commentText;
    private Integer commentTo;
    private Boolean commentStatus;
    private String commentFloor;
}
