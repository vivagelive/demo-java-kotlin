package com.itechart.demojavakotlin.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "movie")
public class MovieEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "text", nullable = false, unique = true)
    private String name;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "movieId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TicketEntity> tickets;
}
