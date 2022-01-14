package com.jpa.basic.test.embeddedId;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Child2 {

    @EmbeddedId
    private ChildId childId;

    private String name;

    @MapsId("parentId")
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent2 parent;

    @Builder
    private Child2(ChildId childId, String name, Parent2 parent) {
        this.childId = childId;
        this.name = name;
        this.parent = parent;
    }

    @Data
    @NoArgsConstructor
    @Embeddable
    public static class ChildId implements Serializable {

        private Long parentId;

        @SequenceGenerator(
                name = "child_id_seq2",
                sequenceName = "child_id_seq2",
                allocationSize = 1
        )
        @Column(name = "CHILD_ID") @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "child_id_seq2")
        private Long id;
    }
}
