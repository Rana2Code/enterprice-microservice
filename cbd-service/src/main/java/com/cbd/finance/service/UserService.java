package com.cbd.finance.service;

import com.cbd.finance.exception.EntityNotFoundException;
import com.cbd.finance.model.dto.User;
import com.cbd.finance.model.entity.UserEntity;
import com.cbd.finance.model.mapper.UserMapper;
import com.cbd.finance.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserMapper userMapper = new UserMapper();

    @Autowired  
    UserRepository userRepository;

    public User readUser(String identification) {
        UserEntity userEntity = userRepository.findByIdentificationNumber(identification).orElseThrow(EntityNotFoundException::new);
        return userMapper.convertToDto(userEntity);
    }

    public List<User> readUsers(Pageable pageable) {
        return userMapper.convertToDtoList(userRepository.findAll(pageable).getContent());
    }
}
