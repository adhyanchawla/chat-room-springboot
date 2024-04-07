package com.chat.app.ChatRoomApp;

import com.chat.app.ChatRoomApp.repositiories.MessageRepo;
import com.chat.app.ChatRoomApp.repositiories.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
public class ChatRoomAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatRoomAppApplication.class, args);
	}

}
