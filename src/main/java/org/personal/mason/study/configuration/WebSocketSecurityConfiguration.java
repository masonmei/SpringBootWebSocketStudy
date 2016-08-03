package org.personal.mason.study.configuration;

import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

/**
 * Created by meidongxu on 6/22/15.
 */

public class WebSocketSecurityConfiguration extends AbstractSecurityWebSocketMessageBrokerConfigurer {

@Override
protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {

   messages.anyMessage().authenticated();
}

@Override
protected boolean sameOriginDisabled() {

   return true;
}
}
