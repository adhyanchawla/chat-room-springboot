package com.chat.app.ChatRoomApp.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Message {
    @Id
    private String id;
    private String chatId;
    private String recipientId;
    private String senderId;
    private String content;
    private Date timestamp;
}
