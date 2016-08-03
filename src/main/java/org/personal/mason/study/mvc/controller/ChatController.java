package org.personal.mason.study.mvc.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.personal.mason.study.event.LoginEvent;
import org.personal.mason.study.event.ParticipantRepository;
import org.personal.mason.study.exceptions.TooManyProfanityException;
import org.personal.mason.study.model.ChatMessage;
import org.personal.mason.study.model.SessionProfanity;
import org.personal.mason.study.util.ProfanityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import java.security.Principal;
import java.util.Collection;

/**
 * Created by meidongxu on 6/22/15.
 */
@Controller
public class ChatController {

@Autowired
private ProfanityChecker profanityFilter;

@Autowired
private SessionProfanity profanity;

@Autowired
private WebSocketMessageBrokerStats stats;

@Autowired
private ParticipantRepository participantRepository;

@Autowired
private SimpMessagingTemplate simpMessagingTemplate;

@Autowired
private CommandGateway commandGateway;

@SubscribeMapping("/chat.participants")
public Collection<LoginEvent> retrieveParticipants() {

   return participantRepository.getActiveSessions().values();
}

@MessageMapping("/chat.message")
public ChatMessage filterMessage(@Payload ChatMessage message, Principal principal) {

   checkProfanityAndSanitize(message);

   message.setUsername(principal.getName());
   return message;
}

@MessageMapping("/chat.private.{username}")
public void filterPrivateMessage(@Payload ChatMessage message, @DestinationVariable("username") String username,
                                 Principal principal) {

   checkProfanityAndSanitize(message);

   message.setUsername(username);

   simpMessagingTemplate.convertAndSend("/user/" + username + "/queue/chat.message", message);
}

private void checkProfanityAndSanitize(ChatMessage message) {

   long profanityLevel = profanityFilter.getMessageProfanity(message.getMessage());

   profanity.increment(profanityLevel);
   message.setMessage(profanityFilter.filter(message.getMessage()));
}

@MessageExceptionHandler
@SendToUser(value = "/queue/errors", broadcast = false)
public String handleProfanity(TooManyProfanityException e) {

   return e.getMessage();
}

@RequestMapping("/stats")
@ResponseBody
public WebSocketMessageBrokerStats showStats() {

   return stats;
}
}
