package com.chat.app.ChatRoomApp.controllers;

import com.chat.app.ChatRoomApp.models.ChatNotification;
import com.chat.app.ChatRoomApp.models.Message;
import com.chat.app.ChatRoomApp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
//    @MessageMapping("/message")
//    @SendTo("/topic/return-to") // people who subscribed to this url, message will go to those set of people
//    public Message getContent(@RequestBody Message message) {
//        try {
//            //processing
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//        return message;
//    }
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload Message message) {
        Message savedMessage = this.messageService.save(message);
        // john/queue/messages -> John will be subscribed to messages
        messagingTemplate.convertAndSendToUser(
                message.getRecipientId(), "/queue/messages",
                ChatNotification.builder()
                        .id(savedMessage.getId())
                        .recipientId(savedMessage.getRecipientId())
                        .senderId(savedMessage.getSenderId())
                        .content(savedMessage.getContent())
                        .build()
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<Message>> findMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        return ResponseEntity.ok(messageService.findChatMessages(senderId, recipientId));
    }
}
