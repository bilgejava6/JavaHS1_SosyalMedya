package com.muhammet.config;

import com.muhammet.entity.Auth;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    /**
     * Kuyruğu oluştururken gerekli olan tanımlamalar için sabit değişkenlerimizi tanımlıyoruz.
     * EXCHANGE -> BINDING KEY -> QUEUE
     */
    private final String EXCHANGE_AUTH = "auth-exchange";
    private final String QUEUE_AUTH = "auth-queue";
    private final String BINDING_KEY_AUTH = "auth-binding-key";
    /**
     * Spring Boot RabbitMQ da kullanabilmek üzere sizden belli nesneleri oluşturmanızı ister
     * bunları @Bean ile oluşturarak sisteme enjekte ederiz.
     */

    /**
     * 1. Adım Exchange nesnesini spring e vermemiz gerekli. Burada türüne göre nesen
     * oluşruran bir method tanuımlıyoruz.
     */
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_AUTH);
    }
    @Bean
    Queue createQueue(){
        return new Queue(QUEUE_AUTH);
    }
    @Bean
    Binding bindingAuth(final DirectExchange directExchange,final Queue createQueue){
        return BindingBuilder.bind(createQueue).to(directExchange).with(BINDING_KEY_AUTH);
    }
}
