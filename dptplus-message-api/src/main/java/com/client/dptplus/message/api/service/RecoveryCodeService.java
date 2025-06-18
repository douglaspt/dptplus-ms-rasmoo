package com.client.dptplus.message.api.service;

import com.client.dptplus.message.api.dto.UserRecoveryCodeDto;

public interface RecoveryCodeService {

    void sendEmail(UserRecoveryCodeDto dto);

}
