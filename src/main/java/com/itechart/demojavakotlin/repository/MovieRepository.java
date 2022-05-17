package com.itechart.demojavakotlin.repository;

import com.itechart.demojavakotlin.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {
}
