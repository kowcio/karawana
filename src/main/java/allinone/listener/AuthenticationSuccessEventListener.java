package com.mijasoftware.bluemedia.charon.listener;

import com.mijasoftware.bluemedia.charon.entity.UserEntity;
import com.mijasoftware.bluemedia.charon.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Optional<UserEntity> userEntity = Optional.empty();

        logger.debug("Event {}", event);

        Authentication auth = event.getAuthentication();

        if (auth != null && auth.isAuthenticated() && auth instanceof UsernamePasswordAuthenticationToken) {
            userEntity = Optional.of((UserEntity) auth.getPrincipal());
        }

        logger.debug("Update last login date for user {}", userEntity);

        userService.updateLastLoginDate(userEntity);
    }
}