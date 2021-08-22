package com.blank.epicfserver;

import com.blank.epicfserver.security.services.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {

    public static Long getUserId() {
        return  ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

}
