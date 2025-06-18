package com.client.dptplus.message.api.dto;

import java.time.LocalDateTime;

public record UserRecoveryCodeDto(
        String email,
        String code,
        LocalDateTime creationDate

) {
}
