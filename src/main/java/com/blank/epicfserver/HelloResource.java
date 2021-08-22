package com.blank.epicfserver;

import com.blank.epicfserver.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/hello")
public class HelloResource {

    Logger logger = LoggerFactory.getLogger(HelloResource.class);

    @GetMapping( {"/me"})
    public ResponseEntity<?> hihihi(HttpSession session) {
        String emailFromCtx =  ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();

        logger.info("User " + emailFromCtx);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
