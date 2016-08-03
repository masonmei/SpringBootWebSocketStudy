package org.personal.mason.study.domain.imsg.api.event;

import org.personal.mason.study.domain.imsg.api.MessageDetails;

/**
 * Created by meidongxu on 6/29/15.
 */
public class MessageSendToConversationEvent {

private final MessageDetails messageDetails;

public MessageSendToConversationEvent(final MessageDetails messageDetails) {
   this.messageDetails = messageDetails;
}

public MessageDetails getMessageDetails() {
   return messageDetails;
}
}
