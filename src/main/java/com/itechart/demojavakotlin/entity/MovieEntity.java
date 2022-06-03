package com.itechart.demojavakotlin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
public class MovieEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "description")
    private String description;

    @Column(name = "tickets_quantity")
    private Integer ticketsQuantity;

    @Column(name = "ticket_price")
    private BigDecimal ticketPrice;

    @JsonIgnore
    @OneToMany(mappedBy = "movieId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TicketEntity> tickets;
}
