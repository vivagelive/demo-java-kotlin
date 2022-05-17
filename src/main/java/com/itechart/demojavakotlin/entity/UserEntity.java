package com.itechart.demojavakotlin.entity;
import lombok.NoArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private UUID id;

    @Column(name = "money")
    private double money;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TicketEntity> tickets;
}
