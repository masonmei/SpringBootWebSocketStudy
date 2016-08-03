package org.personal.mason.study.tasks;

import org.personal.mason.study.event.LoginEvent;
import org.personal.mason.study.event.ParticipantRepository;
import org.personal.mason.study.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by meidongxu on 6/22/15.
 */
@Component
public class RandomMessageGenerator implements ApplicationListener<BrokerAvailabilityEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(RandomMessageGenerator.class);

    private final String[] MSGS = new String[]{"I miss you!", "Baby, miss you, too!"};

    @Autowired
    private ParticipantRepository participantRepository;

    private MessageSendingOperations<String> messageTemplate;

    @Autowired
    public RandomMessageGenerator(MessageSendingOperations<String> messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @Override
    public void onApplicationEvent(BrokerAvailabilityEvent event) {

    }

    @Scheduled(fixedDelay = 3000)
    public void generateAndSendMsg() {
        Random random = new Random();
        ChatMessage payload = new ChatMessage();
        payload.setMessage(MSGS[random.nextInt(100) % 2]);
        Map<String, LoginEvent> activeSessions = participantRepository.getActiveSessions();
        if (activeSessions.size() == 0) {
            LOG.debug("ignore this msg generating");
            return;
        }
        int counter = random.nextInt(activeSessions.size());
        Iterator<LoginEvent> iterator = activeSessions.values().iterator();

        LoginEvent next = null;
        while (iterator.hasNext() && counter > 0) {
            next = iterator.next();
            counter--;
        }

        if (next == null) {
            return;
        }

        String username = next.getUsername();
        payload.setUsername(username);

        this.messageTemplate.convertAndSend("/user/" + username + "/queue/chat.message", payload);
    }
}
