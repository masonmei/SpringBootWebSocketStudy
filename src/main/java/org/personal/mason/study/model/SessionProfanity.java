package org.personal.mason.study.model;

import org.personal.mason.study.exceptions.TooManyProfanityException;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by meidongxu on 6/22/15.
 */
public class SessionProfanity {

private long maxProfanityLevel = Long.MAX_VALUE;

private AtomicLong profanityLevel = new AtomicLong();

public SessionProfanity() {
}

public SessionProfanity(long maxProfanityLevel) {
   this.maxProfanityLevel = maxProfanityLevel;
}

public void increment(long partialProfanity) {
   if (profanityLevel.intValue() + partialProfanity >= maxProfanityLevel) {
      profanityLevel.set(maxProfanityLevel);
      throw new TooManyProfanityException("You have reached the max profanity level. You are banned!");
   }
   profanityLevel.addAndGet(partialProfanity);
}

}
