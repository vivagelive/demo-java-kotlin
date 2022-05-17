package com.itechart.demojavakotlin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private UUID id;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movieId;
}
