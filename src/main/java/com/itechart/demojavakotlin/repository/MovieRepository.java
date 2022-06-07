package com.itechart.demojavakotlin.repository;

import com.itechart.demojavakotlin.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {

    Optional<MovieEntity> findByName(final String movieName);

    @Modifying
    @Query(value = "UPDATE movie SET tickets_quantity = :quantity, ticket_price = :ticketPrice WHERE id = :id", nativeQuery = true)
    void changeTotalTicketsQuantity(@Param("quantity") final int quantity, @Param("id") final UUID id, @Param("ticketPrice") final BigDecimal ticketPrice);
}
