package com.chat.app.ChatRoomApp.repositiories;

import com.chat.app.ChatRoomApp.models.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepo extends MongoRepository<ChatRoom, String> {
    Optional<String> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
