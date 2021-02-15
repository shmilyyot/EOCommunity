package it.eogroup.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageBoard {
    Integer messageId;
    String messageText;
    LocalDateTime messageTime;
}
