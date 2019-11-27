package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user")
@Entity
public class TopChefUser {
    @Id
    @Column(name = "u_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "u_name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "create_date")
    private String createDate;
}
