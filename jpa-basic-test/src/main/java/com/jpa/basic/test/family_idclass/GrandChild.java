package com.jpa.basic.test.family_idclass;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(GrandChild.GrandChildId.class)
@Entity
public class GrandChild {

    @SequenceGenerator(
            name = "grandchild_id_seq",
            sequenceName = "grandchild_id_seq",
            allocationSize = 1
    )
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grandchild_id_seq")
    @Column(name = "GRANDCHILD_ID")
    private Long id;

    @Id
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "PARENT_ID", referencedColumnName = "PARENT_ID"),
        @JoinColumn(name = "CHILD_ID", referencedColumnName = "CHILD_ID")
    })
    private Child child;

    private String name;

    @Builder
    private GrandChild(Child child, String name) {
        this.child = child;
        this.name = name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GrandChildId implements Serializable {

        private Long id;
        private Child.ChildId child;
    }
}
