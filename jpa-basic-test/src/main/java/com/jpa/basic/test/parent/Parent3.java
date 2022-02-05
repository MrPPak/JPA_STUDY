package com.jpa.basic.test.parent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Parent3 {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child3> children = new ArrayList<>();

    public Parent3(String name) {
        this.name = name;
    }

    public void addChild(Child3 child) {
        children.add(child);
        child.setParent(this);
    }
}
