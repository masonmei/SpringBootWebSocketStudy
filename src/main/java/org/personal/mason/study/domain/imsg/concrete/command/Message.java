package org.personal.mason.study.domain.imsg.concrete.command;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.personal.mason.study.domain.imsg.api.MessageDetails;
import org.personal.mason.study.domain.imsg.api.MessageId;

/**
 * Created by meidongxu on 6/29/15.
 */
public class Message extends AbstractAnnotatedAggregateRoot<MessageId> {

private static final long serialVersionUID = -3695006750328154106L;

public Message(final MessageDetails messageDetails) {

}
}
