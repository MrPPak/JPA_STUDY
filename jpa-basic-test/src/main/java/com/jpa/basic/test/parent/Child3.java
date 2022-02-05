package com.jpa.basic.test.parent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Child3 {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent3 parent;

    public Child3(String name) {
        this.name = name;
    }

    public void setParent(Parent3 parent) {
        this.parent = parent;
    }

    public void changeParent(Parent3 parent) {
        this.parent.getChildren().remove(this);
        this.parent = parent;
        this.parent.getChildren().add(this);
    }
}
