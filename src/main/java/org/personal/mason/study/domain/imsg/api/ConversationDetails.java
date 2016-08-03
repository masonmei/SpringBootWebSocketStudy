package org.personal.mason.study.domain.imsg.api;

import java.util.List;

/**
 * Created by meidongxu on 6/29/15.
 */
public interface ConversationDetails {

ConversationId getConversationId();

ConversationType getConversationType();

List<String> getSubscribers();

List<MessageDetails> getConversationMessages();

}
