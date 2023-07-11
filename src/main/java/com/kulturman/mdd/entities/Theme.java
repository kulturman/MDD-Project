package com.kulturman.mdd.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "themes")
@Data
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}
