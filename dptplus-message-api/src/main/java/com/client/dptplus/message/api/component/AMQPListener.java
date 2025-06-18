package com.client.dptplus.message.api.component;

import com.client.dptplus.message.api.dto.UserRecoveryCodeDto;
import com.client.dptplus.message.api.service.RecoveryCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AMQPListener {

    private final RecoveryCodeService recoveryCodeService;

    @RabbitListener(queues = "recovery.code.email")
    public void recoveryCodeListener(UserRecoveryCodeDto dto) {
        recoveryCodeService.sendEmail(dto);
    }

}
