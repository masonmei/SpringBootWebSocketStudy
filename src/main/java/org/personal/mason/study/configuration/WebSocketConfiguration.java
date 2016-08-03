package org.personal.mason.study.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.session.ExpiringSession;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by meidongxu on 6/22/15.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractSessionWebSocketMessageBrokerConfigurer<ExpiringSession> {

@Override
protected void configureStompEndpoints(StompEndpointRegistry registry) {

   registry.addEndpoint("/ws").withSockJS();
}

@Override
public void configureMessageBroker(MessageBrokerRegistry registry) {

   registry.enableSimpleBroker("/queue/", "/topic/");

   registry.setApplicationDestinationPrefixes("/app");
}
}
