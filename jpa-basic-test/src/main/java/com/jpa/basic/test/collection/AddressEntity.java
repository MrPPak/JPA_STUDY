package com.jpa.basic.test.collection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ADDRESS")
@Entity
public class AddressEntity {

    @Id @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public AddressEntity(String city, String street, String zipcode, Member member) {
        this.address = new Address(city, street, zipcode);
        this.member = member;
    }

}
