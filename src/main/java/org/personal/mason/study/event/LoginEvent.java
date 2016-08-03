package org.personal.mason.study.event;

import java.util.Date;

/**
 * Created by meidongxu on 6/22/15.
 */
public class LoginEvent {

private String username;
private Date loginTime;

public LoginEvent(String username) {
   this.username = username;
   this.loginTime = new Date();
}

public String getUsername() {
   return username;
}

public void setUsername(String username) {
   this.username = username;
}

public Date getLoginTime() {
   return loginTime;
}

public void setLoginTime(Date loginTime) {
   this.loginTime = loginTime;
}
}
