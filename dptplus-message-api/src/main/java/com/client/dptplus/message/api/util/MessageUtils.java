package com.client.dptplus.message.api.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageUtils {

    private final MessageSource messageSource;

    public String get(String code) {
        return messageSource.getMessage(code, null, null);
    }
}
