package org.personal.mason.study.domain.imsg.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.personal.mason.study.domain.imsg.api.ConversationId;
import org.springframework.util.Assert;

/**
 * Created by meidongxu on 6/29/15.
 */
public class CloseConversationCommand {

@TargetAggregateIdentifier
private final ConversationId conversationId;

public CloseConversationCommand(final ConversationId conversationId) {
   Assert.notNull(conversationId, "ConversationId may not be null.");
   this.conversationId = conversationId;
}

public ConversationId getConversationId() {
   return conversationId;
}
}
