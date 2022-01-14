package com.jpa.basic.test.idclass;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(MultiKey.MultiKeyId.class)
@Entity
public class MultiKey {

    @SequenceGenerator(
            name = "multikey_id_seq1",
            sequenceName = "multikey_id_seq1",
            allocationSize = 1
    )
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "multikey_id_seq1")
    @Column(name = "MULTIKEY_ID1")
    private Long id1;

    @SequenceGenerator(
            name = "multikey_id_seq2",
            sequenceName = "multikey_id_seq2",
            allocationSize = 1
    )
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "multikey_id_seq2")
    @Column(name = "MULTIKEY_ID2")
    private Long id2;

    private String name;

    public MultiKey(String name) {
        this.name = name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultiKeyId implements Serializable {

        private Long id1;
        private Long id2;
    }
}
