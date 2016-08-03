package org.personal.mason.study.domain.imsg.api.event;

import org.personal.mason.study.domain.imsg.api.ConversationDetails;

/**
 * Created by meidongxu on 6/29/15.
 */
public class ConversationStartedEvent {

private final ConversationDetails details;

public ConversationStartedEvent(final ConversationDetails details) {
   this.details = details;
}

public ConversationDetails getDetails() {
   return details;
}
}
