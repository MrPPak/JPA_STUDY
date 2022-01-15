package com.jpa.basic.test.transactional;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {

    @SequenceGenerator(
            name = "post_seq",
            sequenceName = "post_seq",
            allocationSize = 1
    )
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    private Long id;

    private String name;

    public Post(String name) {
        this.name = name;
    }
}
