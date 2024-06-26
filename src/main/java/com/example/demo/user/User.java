package com.example.demo.user;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "appuser")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 20, unique = true)
    private String username;

    @Column(name = "telephone", nullable = false)
    private Integer telephone;

    @Column(name = "language", nullable = false, length = 10)
    private String language;

    public User() {

    }

    public User(Long id, String username, Integer telephone, String language) {
        this.id = id;
        this.username = username;
        this.telephone = telephone;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
