package com.jpa.basic.test.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Folder {

    @SequenceGenerator(
            name = "folder_id_seq",
            sequenceName = "folder_id_seq",
            allocationSize = 1
    )
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folder_id_seq")
    @Column(name = "FOLDER_ID")
    private Long id;

    private String name;

    // 부모
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Folder parent;

    // 자식
    @OneToMany(mappedBy = "parent")
    private List<Folder> children;

    @Builder
    private Folder(String name, Folder parent) {
        this.name = name;
        this.parent = parent;
    }
}
