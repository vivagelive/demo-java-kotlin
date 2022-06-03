package com.itechart.demojavakotlin.service;

import com.itechart.demojavakotlin.entity.UserEntity;
import com.itechart.demojavakotlin.model.UserRequest;

import java.math.BigDecimal;
import java.util.UUID;

public interface UserService {

    void payTicket(final UUID id, final BigDecimal bill);

    UserEntity createUser(final UserRequest userRequest);

    UserEntity getByName(final String name);
}
