package com.jpa.basic.test.family_embeddedId;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Parent2 {

    @SequenceGenerator(
            name = "parent_id_seq2",
            sequenceName = "parent_id_seq2",
            allocationSize = 1
    )
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_id_seq2")
    private Long id;

    private String name;

    public Parent2(String name) {
        this.name = name;
    }
}
