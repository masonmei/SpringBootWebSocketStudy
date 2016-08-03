package org.personal.mason.study.domain.imsg.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.personal.mason.study.domain.imsg.api.ConversationId;
import org.springframework.util.Assert;

/**
 * Created by meidongxu on 6/29/15.
 */
public class JoinConversationCommand {

@TargetAggregateIdentifier
private final ConversationId conversationId;
private final String subscriber;

public JoinConversationCommand(final ConversationId conversationId, final String subscriber) {
   Assert.notNull(conversationId, "ConversationId may not be null.");
   Assert.hasLength(subscriber, "Subscriber may not have length.");

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
