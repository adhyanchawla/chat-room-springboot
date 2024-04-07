package com.chat.app.ChatRoomApp.services;

import com.chat.app.ChatRoomApp.models.Message;
import com.chat.app.ChatRoomApp.repositiories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ChatRoomService chatRoomService;

    public Message save(Message message) {
        String chatId = this.chatRoomService.getChatRoomId(message.getSenderId(), message.getRecipientId(), true).orElseThrow();
        message.setChatId(chatId);
        return messageRepo.save(message);
    }

    public List<Message> findChatMessages(String senderId, String recipientId) {
        String chatId = this.chatRoomService.getChatRoomId(senderId, recipientId, false).orElseThrow();
        return messageRepo.findByChatId(chatId);
    }

}
