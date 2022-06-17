package com.itechart.demojavakotlin.repository;

import com.itechart.demojavakotlin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByName(final String name);

    @Query(value = "SELECT money FROM users WHERE id = :id", nativeQuery = true)
    BigDecimal getUsersMoney(@Param("id") final UUID id);

    @Modifying
    @Query(value = "UPDATE users SET money = money - :bill WHERE id = :id", nativeQuery = true)
    void payTicket(@Param("id") final UUID id, @Param("bill") final BigDecimal bill);

    @Query(value = "SELECT EXISTS(SELECT * FROM users WHERE id = :id AND money IS NOT NULL)",nativeQuery = true)
    boolean checkUsersMoneyNotNull(@Param("id") final UUID id);
}
