package com.itechart.demojavakotlin.service.impl;

import com.itechart.demojavakotlin.entity.UserEntity;
import com.itechart.demojavakotlin.exceptions.EntityNotFoundException;
import com.itechart.demojavakotlin.exceptions.UnprocessableException;
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
        //todo #DONE 1. check money
        final BigDecimal usersMoney = userRepository.getUsersMoney(id);
        if (usersMoney.compareTo(bill) < 0) {
            throw new UnprocessableException("User dont have enough money");
        }
        //todo #DONE 2. gimme your money
        userRepository.payTicket(id, bill);
    }

    @Override
    @Transactional
    public UserEntity createUser(final UserRequest userRequest) {
        //todo #DONE
        if (userRequest.getName() == null || userRequest.getName().isEmpty()) {
            throw new UnprocessableException("Username is null or empty");
        }
        return userRepository.saveAndFlush(UserEntity.builder()
                .name(userRequest.getName())
                .money(userRequest.getMoney())
                .build());
    }

    @Override
    public UserEntity getByName(final String name) {
        //todo #DONE
        return userRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
