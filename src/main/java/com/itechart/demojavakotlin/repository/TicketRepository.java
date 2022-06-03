package com.itechart.demojavakotlin.repository;

import com.itechart.demojavakotlin.entity.MovieEntity;
import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {

    @Modifying
    void deleteByMovieId(final MovieEntity movieId);

//    @Query(value = "SELECT COUNT(*) FROM ticket where user_id =", nativeQuery = true)
//    Integer countTicketEntitiesByUserIdA(final UserEntity user);
    List<TicketEntity> findByUserId(final UserEntity user);
}
