package org.personal.mason.study.domain.imsg.concrete.command;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.personal.mason.study.domain.imsg.api.ConversationDetails;
import org.personal.mason.study.domain.imsg.api.ConversationId;
import org.personal.mason.study.domain.imsg.api.ConversationType;
import org.personal.mason.study.domain.imsg.api.command.JoinConversationCommand;
import org.personal.mason.study.domain.imsg.api.command.LeaveConversationCommand;
import org.personal.mason.study.domain.imsg.api.command.SendMessageToConversationCommand;
import org.personal.mason.study.domain.imsg.api.event.ConversationClosedEvent;
import org.personal.mason.study.domain.imsg.api.event.ConversationStartedEvent;
import org.personal.mason.study.domain.imsg.api.event.JoinedConversationEvent;
import org.personal.mason.study.domain.imsg.api.event.LeavedConversationEvent;
import org.personal.mason.study.domain.imsg.api.event.MessageSendToConversationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by meidongxu on 6/29/15.
 */
public class Conversation extends AbstractAnnotatedAggregateRoot<ConversationId> {

private static final long serialVersionUID = 7435953610925894245L;

private static final Logger LOG = LoggerFactory.getLogger(Conversation.class);

@AggregateIdentifier
private ConversationId conversationId;

private ConversationType conversationType;

@EventSourcedMember
private List<Message> messages = new ArrayList<>();

private List<String> subscribers = new ArrayList<>();

private Conversation() {
}

public Conversation(ConversationDetails details) {
   apply(new ConversationStartedEvent(details));
}

public void close() {
   markDeleted();
   apply(new ConversationClosedEvent(conversationId));
}

public void join(JoinConversationCommand command) {
   apply(new JoinedConversationEvent(command.getSubscriber(), command.getConversationId()));
}

public void leave(LeaveConversationCommand command) {
   apply(new LeavedConversationEvent(command.getSubscriber(), command.getConversationId()));
}

public void sendMessage(SendMessageToConversationCommand command) {
   apply(new MessageSendToConversationEvent(command.getMessageDetails()));
}

@EventSourcingHandler
public void on(ConversationStartedEvent event) {
   this.conversationId = event.getDetails().getConversationId();
   this.conversationType = event.getDetails().getConversationType();
   this.messages = event.getDetails().getConversationMessages()
                        .stream()
                        .map(messageDetails -> new Message(messageDetails))
                        .collect(Collectors.toList());
   this.subscribers = event.getDetails().getSubscribers();
}

@EventSourcingHandler
public void on(JoinedConversationEvent event) {
   String subscriber = event.getSubscriber();
   if (!this.subscribers.contains(subscriber)) {
      subscribers.add(subscriber);
   }
}

@EventSourcingHandler
public void on(LeavedConversationEvent event) {
   String subscriber = event.getSubscriber();
   if (!this.subscribers.contains(subscriber)) {
      subscribers.remove(subscriber);
   }
}

@EventSourcingHandler
public void on(MessageSendToConversationEvent event) {
   String sender = event.getMessageDetails().getSender();
   if(subscribers.contains(sender)){
      messages.add(new Message(event.getMessageDetails()));
   }
}
}
