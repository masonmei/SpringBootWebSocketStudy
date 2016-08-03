package org.personal.mason.study.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by meidongxu on 6/22/15.
 */
public class ParticipantRepository {

private Map<String, LoginEvent> activeSessions = new ConcurrentHashMap<>();

public void add(String session, LoginEvent event) {
   activeSessions.put(session, event);
}

public LoginEvent get(String session) {
   return activeSessions.get(session);
}

public void remove(String session) {
   activeSessions.remove(session);
}

public Map<String, LoginEvent> getActiveSessions() {
   return activeSessions;
}

public void setActiveSessions(Map<String, LoginEvent> activeSessions) {
   this.activeSessions = activeSessions;
}
}
