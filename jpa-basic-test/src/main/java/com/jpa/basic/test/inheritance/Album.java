package com.jpa.basic.test.inheritance;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("ALBUM")
@Entity
public class Album extends Item {

    private String artist;

    @Builder
    public Album(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }
}
