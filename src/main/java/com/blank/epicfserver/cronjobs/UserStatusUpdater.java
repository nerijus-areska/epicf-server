package com.blank.epicfserver.cronjobs;

import com.blank.epicfserver.model.User;
import com.blank.epicfserver.repository.UserRepository;
import com.blank.epicfserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserStatusUpdater {
    private static final Logger log = LoggerFactory.getLogger(UserStatusUpdater.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    UserService userService;

    @Scheduled(fixedRate = 6000)
    public void addEnergy() {
        //log.info("The time is now {}", dateFormat.format(new Date()));
        userService.addEnergy(1L, 5);
    }
}