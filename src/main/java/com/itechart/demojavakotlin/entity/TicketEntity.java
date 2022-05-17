package com.itechart.demojavakotlin.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movieId;
}
