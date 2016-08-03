package org.personal.mason.study.domain.imsg.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.personal.mason.study.domain.imsg.api.ConversationDetails;
import org.personal.mason.study.domain.imsg.api.ConversationId;
import org.springframework.util.Assert;

/**
 * Created by meidongxu on 6/29/15.
 */
public class StartConversationCommand {

@TargetAggregateIdentifier
private final ConversationId conversationId;
private final ConversationDetails conversationDetails;

public StartConversationCommand(final ConversationDetails conversationDetails) {
   Assert.notNull(conversationDetails, "ConversationDetails may not be null.");
   Assert.notNull(conversationDetails.getConversationType(), "ConversationType may not be null.");
   Assert.notNull(conversationDetails.getConversationId(), "ConversationId may not be null.");

   this.conversationId = conversationDetails.getConversationId();
   this.conversationDetails = conversationDetails;
}

public ConversationId getConversationId() {
   return conversationId;
}

public ConversationDetails getConversationDetails() {
   return conversationDetails;
}
}
