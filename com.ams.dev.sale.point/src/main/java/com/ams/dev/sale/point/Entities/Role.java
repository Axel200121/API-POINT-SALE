package com.ams.dev.sale.point.Entities;


import jakarta.persistence.*;

import java.util.Set;

@Table(name = "role")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "role")
    private Set<User> users;
}
