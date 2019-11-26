package com.topchef.demo.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tag")
@Entity(name = "smartkitchen")
public class TopchefTag {
    @Id
    @Column(name = "id")
    private String tagId;

    @Column(name = "name")
    private String name;
}
