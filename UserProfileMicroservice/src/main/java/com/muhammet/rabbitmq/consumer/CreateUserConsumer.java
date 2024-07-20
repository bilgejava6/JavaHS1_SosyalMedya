package com.muhammet.rabbitmq.consumer;

import com.muhammet.rabbitmq.model.CreateAuthModel;
import com.muhammet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserConsumer {
    private final UserProfileService userProfileService;
    @RabbitListener(queues = "auth-queue")
    public void createUserListerner(CreateAuthModel model){
      log.info("Mesaj Geldi....: "+ model);
      userProfileService.save(model);
    }
}
