package org.personal.mason.study.configuration;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.commandhandling.disruptor.DisruptorConfiguration;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.jdbc.JdbcEventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.sql.DataSource;

/**
 * Created by meidongxu on 6/28/15.
 */
@Configuration
public class AxonConfiguration {

@Bean
public AnnotationEventListenerBeanPostProcessor eventListenerBeanPostProcessor(EventBus eventBus) {
   Assert.notNull(eventBus, "EventBus may not be null.");

   AnnotationEventListenerBeanPostProcessor postProcessor = new AnnotationEventListenerBeanPostProcessor();
   postProcessor.setEventBus(eventBus);

   return postProcessor;
}

@Bean
public AnnotationCommandHandlerBeanPostProcessor commandHandlerBeanPostProcessor(CommandBus commandBus) {
   Assert.notNull(commandBus, "CommandBus may not be null.");

   AnnotationCommandHandlerBeanPostProcessor postProcessor = new AnnotationCommandHandlerBeanPostProcessor();
   postProcessor.setCommandBus(commandBus);

   return postProcessor;
}

@Bean
public CommandBus commandBus(final EventStore eventStore,  final EventBus eventBus) {
   Assert.notNull(eventStore, "EventStore may not be null.");
   Assert.notNull(eventBus, "EventBus may not be null.");

   final DisruptorConfiguration configuration = new DisruptorConfiguration();
   // Custom Settings

   final DisruptorCommandBus commandBus = new DisruptorCommandBus(eventStore, eventBus, configuration);

   return commandBus;
}

@Bean
public EventBus eventBus() {
   final SimpleEventBus eventBus = new SimpleEventBus();

   return eventBus;
}

@Bean
public EventStore eventStore(final DataSource dataSource) {
   final JdbcEventStore eventStore = new JdbcEventStore(dataSource);

   return eventStore;
}

@Bean
public CommandGatewayFactoryBean<CommandGateway> commandGatewayFactoryBean(CommandBus commandBus) {
   CommandGatewayFactoryBean<CommandGateway> factory = new CommandGatewayFactoryBean<>();
   factory.setCommandBus(commandBus);

   return factory;
}
}
