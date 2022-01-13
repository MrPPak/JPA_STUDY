package com.jpa.basic.test.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parent {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

    public Parent(String name) {
        this.name = name;
    }
}
