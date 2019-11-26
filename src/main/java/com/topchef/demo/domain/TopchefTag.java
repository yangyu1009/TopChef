package com.topchef.demo.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class TopchefTag {
    @Id
    @Column
    private String tagId;

    @Column
    private String name;
}
