package com.jpa.basic.test.proxy;

public class DeveloperInterfaceProxy implements DeveloperInterface{

    DeveloperInterface developer;

    public DeveloperInterfaceProxy(DeveloperInterface developer) {
        this.developer = developer;
    }

    @Override
    public void develop() {
        System.out.println("오늘은 무엇을 하였느냐?");
        developer.develop();
        System.out.println("오 그렇구나!");
    }
}
