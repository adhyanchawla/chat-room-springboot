package com.chat.app.ChatRoomApp.services;

import com.chat.app.ChatRoomApp.models.ChatRoom;
import com.chat.app.ChatRoomApp.repositiories.ChatRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepo chatRoomRepo;

    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists) {
        return this.chatRoomRepo.findBySenderIdAndRecipientId(senderId, recipientId).or(()->{
           if(createNewRoomIfNotExists) {
                String chatId = createChat(senderId, recipientId);
                return Optional.of(chatId);
           }
           return Optional.empty();
        });
    }

    public String createChat(String senderId, String recipientId) {
        String chatId = String.format("%s_%s", senderId, recipientId);
        ChatRoom senderRecipient = ChatRoom.builder()
                    .chatId(chatId)
                    .recipientId(recipientId)
                    .senderId(senderId)
                .build();

        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .recipientId(senderId)
                .senderId(recipientId)
                .build();

        chatRoomRepo.save(senderRecipient);
        chatRoomRepo.save(recipientSender);
        return chatId;

    }
}
