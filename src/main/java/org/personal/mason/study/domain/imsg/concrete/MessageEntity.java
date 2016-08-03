package org.personal.mason.study.domain.imsg.concrete;

import org.personal.mason.study.domain.imsg.api.ConversationId;
import org.personal.mason.study.domain.imsg.api.MessageDetails;
import org.personal.mason.study.domain.imsg.api.MessageId;

import java.util.Date;

/**
 * Created by meidongxu on 6/29/15.
 */
public class MessageEntity implements MessageDetails {

private MessageId messageId;
private String sender;
private ConversationId conversationId;
private Date sendTime;
private String content;

@Override
public MessageId getMessageId() {
   return messageId;
}

public void setMessageId(final MessageId messageId) {
   this.messageId = messageId;
}

@Override
public String getSender() {
   return sender;
}

public void setSender(final String sender) {
   this.sender = sender;
}

@Override
public ConversationId getConversationId() {
   return conversationId;
}

public void setConversationId(final ConversationId conversationId) {
   this.conversationId = conversationId;
}

@Override
public Date getSendTime() {
   return sendTime;
}

public void setSendTime(final Date sendTime) {
   this.sendTime = sendTime;
}

@Override
public String getContent() {
   return content;
}

public void setContent(final String content) {
   this.content = content;
}
}
