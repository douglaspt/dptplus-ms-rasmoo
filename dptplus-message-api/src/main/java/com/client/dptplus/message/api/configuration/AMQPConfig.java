package com.client.dptplus.message.api.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    private static final String RECOVERY_CODE_EMAIL = "recovery.code.email";
    private static final String RECOVERY_CODE_DLQ = "recovery.code.dlq";

    private static final String RECOVERY_CODE_EXCHANGE = "recovery.code.ex";
    private static final String RECOVERY_CODE_DLX = "recovery.code.dlx";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory conn, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(conn);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public Queue recoveryCodeQueue() {
        return QueueBuilder
                .nonDurable(RECOVERY_CODE_EMAIL)
                .deadLetterExchange(RECOVERY_CODE_DLX)
                .build();
    }

    @Bean
    public Queue recoveryCodeDLQ() {
        return QueueBuilder
                .nonDurable(RECOVERY_CODE_DLQ).build();
    }

    @Bean
    public FanoutExchange recoveryCodeExchange() {
        return new FanoutExchange(RECOVERY_CODE_EXCHANGE);
    }

    @Bean
    public FanoutExchange recoveryCodeDLX() {
        return new FanoutExchange(RECOVERY_CODE_DLX);
    }

    @Bean
    public Binding recoveryCodeBinding() {
        return BindingBuilder
                .bind(recoveryCodeQueue())
                .to(recoveryCodeExchange());
    }

    @Bean
    public Binding recoveryCodeBindingDeadLetter() {
        return BindingBuilder
                .bind(recoveryCodeDLQ())
                .to(recoveryCodeDLX());
    }

}
