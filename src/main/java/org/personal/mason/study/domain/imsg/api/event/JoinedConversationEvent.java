package org.personal.mason.study.domain.imsg.api.event;

import org.personal.mason.study.domain.imsg.api.ConversationId;

/**
 * Created by meidongxu on 6/29/15.
 */
public class JoinedConversationEvent {

private final ConversationId conversationId;
private final String subscriber;

public JoinedConversationEvent(final String subscriber, final ConversationId conversationId) {
   this.conversationId = conversationId;
   this.subscriber = subscriber;
}

public ConversationId getConversationId() {
   return conversationId;
}

public String getSubscriber() {
   return subscriber;
}
}

