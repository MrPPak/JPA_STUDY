package com.jpa.basic.test.multi;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MultiChild {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String childItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no", referencedColumnName = "no")
    MultiParent parent;

    public MultiChild(String childItem, MultiParent parent) {
        this.childItem = childItem;
        this.parent = parent;
    }
}
