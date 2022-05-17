package com.itechart.demojavakotlin.repository;

import com.itechart.demojavakotlin.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {

    @Query(value = "UPDATE ticket SET quantity = quantity - :requestQuantity", nativeQuery = true)
    List<TicketEntity> buyTicket(@Param("requestQuantity")final int quantity);
}
