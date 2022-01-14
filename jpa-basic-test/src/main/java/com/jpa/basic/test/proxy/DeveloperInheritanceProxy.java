package com.jpa.basic.test.proxy;

public class DeveloperInheritanceProxy extends Developer {

    @Override
    public void develop() {
        System.out.println("오늘은 무엇을 하였느냐");
        super.develop();
        System.out.println("오 그렇구나!");
    }
}
