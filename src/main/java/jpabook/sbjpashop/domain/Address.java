package jpabook.sbjpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable //jpa 내장 타입
@Getter
// setter가 없다! => 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스!
// jpa 스펙상 엔티티나 임베디드 타입은 자바 기본 생성자를 public 또는 protected 로 설정해야 한다.

public class Address {

    private String city;
    private String street;

    private String zipcode;

    protected Address() {

    }

    public  Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
