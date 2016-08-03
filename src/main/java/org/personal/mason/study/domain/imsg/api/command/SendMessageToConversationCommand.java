package org.personal.mason.study.domain.imsg.api.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.personal.mason.study.domain.imsg.api.ConversationId;
import org.personal.mason.study.domain.imsg.api.MessageDetails;
import org.springframework.util.Assert;

/**
 * Created by meidongxu on 6/29/15.
 */
public class SendMessageToConversationCommand {

@TargetAggregateIdentifier
private final ConversationId conversationId;
private final MessageDetails messageDetails;

public SendMessageToConversationCommand(final MessageDetails messageDetails) {
   Assert.notNull(messageDetails, "MessageDetails may not be null.");
   Assert.notNull(messageDetails.getConversationId(), "ConversationId may not be null.");
   Assert.hasLength(messageDetails.getSender(), "Sender may not be empty.");
   Assert.hasLength(messageDetails.getContent(), "Content may not be empty.");
   this.conversationId = messageDetails.getConversationId();
   this.messageDetails = messageDetails;
}

public ConversationId getConversationId() {
   return conversationId;
}

public MessageDetails getMessageDetails() {
   return messageDetails;
}
}
