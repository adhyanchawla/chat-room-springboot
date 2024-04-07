package com.chat.app.ChatRoomApp.repositiories;

import com.chat.app.ChatRoomApp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
}
