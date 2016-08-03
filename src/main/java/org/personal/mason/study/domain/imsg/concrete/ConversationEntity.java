package org.personal.mason.study.domain.imsg.concrete;

import org.personal.mason.study.domain.imsg.api.ConversationDetails;
import org.personal.mason.study.domain.imsg.api.ConversationId;
import org.personal.mason.study.domain.imsg.api.ConversationType;
import org.personal.mason.study.domain.imsg.api.MessageDetails;

import java.util.List;

/**
 * Created by meidongxu on 6/29/15.
 */
public class ConversationEntity implements ConversationDetails {

private ConversationId conversationId;
private ConversationType conversationType;
private List<String> subscribers;
private List<MessageDetails> conversationMessages;

@Override
public ConversationId getConversationId() {
   return conversationId;
}

public void setConversationId(final ConversationId conversationId) {
   this.conversationId = conversationId;
}

@Override
public ConversationType getConversationType() {
   return conversationType;
}

public void setConversationType(final ConversationType conversationType) {
   this.conversationType = conversationType;
}

@Override
public List<String> getSubscribers() {
   return subscribers;
}

public void setSubscribers(final List<String> subscribers) {
   this.subscribers = subscribers;
}

@Override
public List<MessageDetails> getConversationMessages() {
   return conversationMessages;
}

public void setConversationMessages(final List<MessageDetails> conversationMessages) {
   this.conversationMessages = conversationMessages;
}
}
