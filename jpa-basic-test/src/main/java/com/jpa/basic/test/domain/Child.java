package com.jpa.basic.test.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(Child.ChildId.class)
public class Child {

    @SequenceGenerator(
            name = "child_id_seq",
            sequenceName = "child_id_seq",
            allocationSize = 1
    )
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "child_id_seq")
    @Column(name = "CHILD_ID")
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    private String name;

    @Builder
    private Child(Parent parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChildId implements Serializable {

        private Long id;
        private Long parent;
    }

}
