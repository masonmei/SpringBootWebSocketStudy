package org.personal.mason.study.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by meidongxu on 6/22/15.
 */
@Configuration
public class RedisConfiguration {

@Bean
public RedisServerBean redisServer() throws IOException, URISyntaxException {
   return new RedisServerBean();
}

static class RedisServerBean implements InitializingBean, DisposableBean, BeanDefinitionRegistryPostProcessor {

   private RedisServer redisServer;

   @Override
   public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
   }

   @Override
   public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
   }

   @Override
   public void destroy() throws Exception {
      if (redisServer != null) {
         redisServer.stop();
      }
   }

   @Override
   public void afterPropertiesSet() throws Exception {
      redisServer = new RedisServer(Protocol.DEFAULT_PORT);
      redisServer.start();
   }

}
}
