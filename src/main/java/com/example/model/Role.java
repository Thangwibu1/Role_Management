package com.example.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RoleID")
    private int id;

    @Column(name = "RoleName", unique = true, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


