package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
public class TopChefUser {
    @Id
    @Column
    private String userId;

    @Column
    private String password;
}
