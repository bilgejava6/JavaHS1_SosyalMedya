package com.muhammet.rabbitmq.producer;

import com.muhammet.rabbitmq.model.CreateAuthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAuthProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(CreateAuthModel model){
        rabbitTemplate.convertAndSend("auth-exchange","auth-binding-key",model);
    }
}
