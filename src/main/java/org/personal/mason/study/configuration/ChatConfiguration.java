package org.personal.mason.study.configuration;

import org.personal.mason.study.event.ParticipantRepository;
import org.personal.mason.study.event.PresenceEventListener;
import org.personal.mason.study.model.SessionProfanity;
import org.personal.mason.study.util.ProfanityChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by meidongxu on 6/22/15.
 */
@Configuration
public class ChatConfiguration {

public static class Destinations {

   private Destinations() {
   }

   private static final String LOGIN = "/topic/chat.login";
   private static final String LOGOUT = "/topic/chat.logout";
}

private static final int MAX_PROFANITY_LEVEL = 5;

@Bean
@Description("Tracks user presence(join/leave) and broadcasts it to all connected users.")
public PresenceEventListener presenceEventListener(ParticipantRepository repository,
                                                   SimpMessagingTemplate messagingTemplate) {
   PresenceEventListener listener = new PresenceEventListener(repository, messagingTemplate);

   listener.setLoginDestination(Destinations.LOGIN);
   listener.setLogoutDestination(Destinations.LOGOUT);
   return listener;
}

@Bean
@Description("Keeps connected users")
public ParticipantRepository participantRepository() {
   return new ParticipantRepository();
}

@Bean
@Description("Utility class to check the number of profanities and filter that.")
public ProfanityChecker profanityFilter() {
   Set<String> profanities = new HashSet<>(Arrays.asList("damn", "ass", "fuck"));
   ProfanityChecker check = new ProfanityChecker();
   check.setProfanities(profanities);
   return check;
}

@Bean
@Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Description("Keep track of the level of profanity of a websocket session")
public SessionProfanity sessionProfanity() {
   return new SessionProfanity(MAX_PROFANITY_LEVEL);
}

}
