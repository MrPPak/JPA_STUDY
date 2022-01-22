package com.jpa.basic.test.multi;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MultiParent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NaturalId
    private Long no;

    private String parentItem;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<MultiChild> children = new ArrayList<>();

    public MultiParent(Long no, String parentItem) {
        this.no = no;
        this.parentItem = parentItem;
    }
}
