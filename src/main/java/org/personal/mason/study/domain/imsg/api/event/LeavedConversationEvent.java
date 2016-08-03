package org.personal.mason.study.domain.imsg.api.event;

import org.personal.mason.study.domain.imsg.api.ConversationId;

/**
 * Created by meidongxu on 6/29/15.
 */
public class LeavedConversationEvent {

private final ConversationId conversationId;
private final String subscriber;

public LeavedConversationEvent(final String subscriber, final ConversationId conversationId) {
   this.subscriber = subscriber;
   this.conversationId = conversationId;
}

public String getSubscriber() {
   return subscriber;
}

public ConversationId getConversationId() {
   return conversationId;
}
}
