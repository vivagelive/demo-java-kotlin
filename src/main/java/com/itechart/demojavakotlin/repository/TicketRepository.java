package com.itechart.demojavakotlin.repository;

import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
    List<TicketEntity> findByUserId(final UserEntity user);
}
