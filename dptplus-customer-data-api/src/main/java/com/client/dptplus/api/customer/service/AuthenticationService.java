package com.client.dptplus.api.customer.service;

import com.client.dptplus.api.customer.dto.LoginDto;
import com.client.dptplus.api.customer.dto.UserDetailsDto;
import com.client.dptplus.api.customer.dto.UserRepresentationDto;

public interface AuthenticationService {

    String auth(LoginDto dto);

    void sendRecoveryCode(String email);

    boolean recoveryCodeIsValid(String recoveryCode, String email);

    void updatePasswordByRecoveryCode(UserDetailsDto userDetailsDto);

    void createAuthUser(UserRepresentationDto userRepresentationDto);

    void updateAuthUser(UserRepresentationDto userRepresentation, String email);

}
