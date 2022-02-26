package com.jpa.basic.test.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime regtm;

    @LastModifiedDate
    private LocalDateTime updtm;

}
