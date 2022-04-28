package sharypov.OrganizationRestApi.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.ErrorHandler;
import sharypov.OrganizationRestApi.properties.ActiveMqProperties;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import static javax.jms.Session.CLIENT_ACKNOWLEDGE;

/**
 * Конфигурация ActiveMq
 */
@Configuration
@EnableJms
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EnableConfigurationProperties({ActiveMqProperties.class})
public class ActiveMqConfig {

    ActiveMqProperties activeMqProperties;

    public ActiveMqConfig(ActiveMqProperties activeMqProperties) {
        this.activeMqProperties = activeMqProperties;
    }

    @Bean("connectionFactory")
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                activeMqProperties.getUser(),
                activeMqProperties.getPassword(),
                activeMqProperties.getBrokerUrl());
        return connectionFactory;
    }

    @Bean("toQueue")
    public Queue toQueue(){
        return new ActiveMQQueue(activeMqProperties.getToQueue());
    }

    @Bean("jmsTemplate")
    public JmsTemplate jmsTemplate(@Qualifier("connectionFactory") ConnectionFactory factory) {
        JmsTemplate jmsTemplate = new JmsTemplate(factory);
        return jmsTemplate;
    }

    @Bean
    public ErrorHandler errorHandler() {
        return new BasicErrorHandler("SampleListener");
    }

    @Bean("sampleListenerFactory")
        public JmsListenerContainerFactory listenerContainerFactory(ConnectionFactory connectionFactory, ErrorHandler errorHandler) {
        val factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionAcknowledgeMode(CLIENT_ACKNOWLEDGE);
        factory.setErrorHandler(errorHandler);
        factory.setConcurrency(activeMqProperties.getListenerConcurrency());
        return factory;
    }
}
