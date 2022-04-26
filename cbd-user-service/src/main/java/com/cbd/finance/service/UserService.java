package com.cbd.finance.service;

import com.cbd.finance.exception.*;
import com.cbd.finance.model.dto.Status;
import com.cbd.finance.model.dto.User;
import com.cbd.finance.model.dto.UserUpdateRequest;
import com.cbd.finance.model.entity.UserEntity;
import com.cbd.finance.model.mapper.UserMapper;
import com.cbd.finance.model.repository.UserRepository;
import com.cbd.finance.model.rest.response.UserResponse;
import com.cbd.finance.service.rest.BankingCoreRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final KeycloakUserService keycloakUserService;
    private final UserRepository userRepository;
    private final BankingCoreRestClient bankingCoreRestClient;

    private UserMapper userMapper = new UserMapper();

    public User createUser(User user) {

        List<UserRepresentation> userRepresentations = keycloakUserService.readUserByEmail(user.getEmail());
        if (userRepresentations.size() > 0) {
            throw new UserAlreadyRegisteredException("This emailid already registered as a user. Please check and retry.", GlobalErrorCode.ERROR_EMAIL_REGISTERED);
        }

        UserResponse userResponse = bankingCoreRestClient.readUser(user.getIdentification());

        if (userResponse.getId() != null) {

            if (!userResponse.getEmail().equals(user.getEmail())) {
                throw new InvalidEmailException("Incorrect emailid. Please check and retry.", GlobalErrorCode.ERROR_INVALID_EMAIL);
            }

            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setEmail(userResponse.getEmail());
            userRepresentation.setEmailVerified(false);
            userRepresentation.setEnabled(false);
            userRepresentation.setUsername(userResponse.getEmail());

            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setValue(user.getPassword());
            credentialRepresentation.setTemporary(false);
            userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

            Integer userCreationResponse = keycloakUserService.createUser(userRepresentation);

            if (userCreationResponse == 201) {
                log.info("User created with given username {}", user.getEmail());

                List<UserRepresentation> userRepresentations1 = keycloakUserService.readUserByEmail(user.getEmail());
                user.setAuthId(userRepresentations1.get(0).getId());
                user.setStatus(Status.PENDING);
                user.setIdentification(userResponse.getIdentificationNumber());
                UserEntity save = userRepository.save(userMapper.convertToEntity(user));
                return userMapper.convertToDto(save);
            }

        }

        throw new InvalidBankingUserException("We couldn't find user with given identification. Please check and retry", GlobalErrorCode.ERROR_USER_NOT_FOUND_UNDER_NIC);

    }


}
