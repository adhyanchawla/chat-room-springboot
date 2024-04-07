package com.chat.app.ChatRoomApp.repositiories;

import com.chat.app.ChatRoomApp.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends MongoRepository<Message, String> {
    List<Message> findByChatId(String chatId);
}
