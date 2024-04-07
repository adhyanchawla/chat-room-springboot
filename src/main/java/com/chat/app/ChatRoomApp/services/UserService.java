package com.chat.app.ChatRoomApp.services;

import com.chat.app.ChatRoomApp.models.Status;
import com.chat.app.ChatRoomApp.models.User;
import com.chat.app.ChatRoomApp.repositiories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        userRepo.save(user);
    }

    public void disconnect(User user) {
        User storedUser = this.userRepo.findById(user.getNickName()).orElseThrow(()->new RuntimeException("User Not Found"));
        user.setStatus(Status.OFFLINE);
        userRepo.save(user);
    }

    public List<User>getAllOnlineUsers() {
        List<User> onlineUsers = this.userRepo.findAll()
                .stream()
                .filter((user)->user.getStatus().equals(Status.ONLINE))
                .collect(Collectors.toList());
        return onlineUsers;
    }
}
