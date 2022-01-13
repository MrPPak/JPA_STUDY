package com.jpa.basic.test.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(Child.ChildId.class)
public class Child {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CHILD_ID")
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    public Child(Parent parent) {
        this.parent = parent;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChildId implements Serializable {

        private Long id;
        private Long parent;
    }

}
