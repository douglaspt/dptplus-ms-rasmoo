package com.client.dptplus.message.api.integration;

import com.client.dptplus.message.api.dto.EmailDto;

public interface MailIntegration {

    void send(EmailDto dto);

    void sendHtmlTemplate(EmailDto dto);

}
