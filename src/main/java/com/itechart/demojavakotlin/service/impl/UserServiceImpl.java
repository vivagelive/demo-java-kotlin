package com.itechart.demojavakotlin.service.impl;

import com.itechart.demojavakotlin.entity.UserEntity;
import com.itechart.demojavakotlin.exceptions.EntityNotFoundException;
import com.itechart.demojavakotlin.model.UserRequest;
import com.itechart.demojavakotlin.repository.UserRepository;
import com.itechart.demojavakotlin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void payTicket(final UUID id, final BigDecimal bill) {
        //todo money check
        userRepository.payTicket(id, bill);
    }

    @Override
    @Transactional
    public UserEntity createUser(final UserRequest userRequest) {
        return userRepository.saveAndFlush(UserEntity.builder()
                .name(userRequest.getName())
                .money(userRequest.getMoney())
                .build());
    }

    @Override
    public UserEntity getByName(final String name) {
        return userRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
