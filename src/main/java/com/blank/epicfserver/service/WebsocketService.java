package com.blank.epicfserver.service;

import com.blank.epicfserver.model.User;
import com.blank.epicfserver.payload.WebsocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebsocketService {

    @Autowired
    SimpMessagingTemplate msgTemplate;

    public void updateUserStatus(User user) {
        msgTemplate.convertAndSend("/message",
                new WebsocketMessage("userStatus", user.genPayload())
        );
    }
}
