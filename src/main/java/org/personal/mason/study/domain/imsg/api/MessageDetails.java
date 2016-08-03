package org.personal.mason.study.domain.imsg.api;

import java.util.Date;

/**
 * Created by meidongxu on 6/29/15.
 */
public interface MessageDetails {

MessageId getMessageId();

String getSender();

ConversationId getConversationId();

Date getSendTime();

String getContent();
}
