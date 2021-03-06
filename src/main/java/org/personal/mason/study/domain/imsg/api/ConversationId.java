package org.personal.mason.study.domain.imsg.api;

import org.axonframework.domain.IdentifierFactory;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Created by meidongxu on 6/29/15.
 */
public class ConversationId implements Serializable {

private static final long serialVersionUID = -346607939945814569L;

private final String identifier;

public ConversationId() {
   this(IdentifierFactory.getInstance().generateIdentifier());
}

public ConversationId(final String identifier) {
   Assert.hasLength(identifier, "identifier may not be empty.");
   this.identifier = identifier;
}

public String getIdentifier() {
   return identifier;
}

@Override
public boolean equals(final Object o) {
   if (this == o) {
      return true;
   }
   if (o == null || getClass() != o.getClass()) {
      return false;
   }

   final ConversationId that = (ConversationId) o;

   if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) {
      return false;
   }

   return true;
}

@Override
public int hashCode() {
   return identifier != null ? identifier.hashCode() : 0;
}

@Override
public String toString() {
   return identifier;
}
}
