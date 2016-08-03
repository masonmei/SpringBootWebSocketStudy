package org.personal.mason.study.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Created by meidongxu on 6/22/15.
 */
public class PresenceEventListener implements ApplicationListener<ApplicationEvent> {

private ParticipantRepository participantRepository;
private SimpMessagingTemplate messagingTemplate;
private String loginDestination;
private String logoutDestination;

public PresenceEventListener(ParticipantRepository participantRepository, SimpMessagingTemplate messagingTemplate) {
   this.participantRepository = participantRepository;
   this.messagingTemplate = messagingTemplate;
}

@Override
public void onApplicationEvent(ApplicationEvent event) {
   if (event instanceof SessionConnectedEvent) {
      handleSessionConnected((SessionConnectedEvent) event);
   } else if (event instanceof SessionDisconnectEvent) {
      handleSessionDisconnected((SessionDisconnectEvent) event);
   }
}

private void handleSessionDisconnected(SessionDisconnectEvent event) {
   LoginEvent loginEvent = participantRepository.get(event.getSessionId());
   if (loginEvent != null) {
      messagingTemplate.convertAndSend(logoutDestination, new LogoutEvent(loginEvent.getUsername()));
      participantRepository.remove(event.getSessionId());
   }
}

private void handleSessionConnected(SessionConnectedEvent event) {
   SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
   String username = headers.getUser().getName();

   LoginEvent loginEvent = new LoginEvent(username);
   messagingTemplate.convertAndSend(loginDestination, loginEvent);
   participantRepository.add(headers.getSessionId(), loginEvent);
}

public ParticipantRepository getParticipantRepository() {
   return participantRepository;
}

public void setParticipantRepository(ParticipantRepository participantRepository) {
   this.participantRepository = participantRepository;
}

public SimpMessagingTemplate getMessagingTemplate() {
   return messagingTemplate;
}

public void setMessagingTemplate(SimpMessagingTemplate messagingTemplate) {
   this.messagingTemplate = messagingTemplate;
}

public String getLoginDestination() {
   return loginDestination;
}

public void setLoginDestination(String loginDestination) {
   this.loginDestination = loginDestination;
}

public String getLogoutDestination() {
   return logoutDestination;
}

public void setLogoutDestination(String logoutDestination) {
   this.logoutDestination = logoutDestination;
}
}
