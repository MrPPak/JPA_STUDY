package com.jpa.basic.test.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Parent {

    @SequenceGenerator(
            name = "parent_id_seq",
            sequenceName = "parent_id_seq",
            allocationSize = 1
    )
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_id_seq")
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

    public Parent(String name) {
        this.name = name;
    }
}
