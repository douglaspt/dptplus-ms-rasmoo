package com.client.dptplus.message.api.dto;

public record EmailDto(
        String mailTo,
        String subject,
        String content
) {
}
