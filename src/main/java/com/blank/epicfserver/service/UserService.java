package com.blank.epicfserver.service;


import com.blank.epicfserver.exception.ConstraintValidationException;
import com.blank.epicfserver.model.User;
import com.blank.epicfserver.payload.WebsocketMessage;
import com.blank.epicfserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static int MAX_ENERGY = 100;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    WebsocketService websocketService;

    @Autowired
    PasswordEncoder encoder;

    public User addUser(User user) {
        if(userRepository.existsByEmail(user.getEmail()))
            throw new ConstraintValidationException("email", "This email already exists");
        user.setPassword(encoder.encode(user.getPassword()))    ;
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public void addEnergy(long userId, int energy) {
        User user = userRepository.findById(userId).get();
        int newEnergy = user.getEnergy() + energy;
        if(newEnergy > 100) {
            newEnergy = 100;
        }
        user.setEnergy(newEnergy);
        websocketService.updateUserStatus(user);
    }
}
