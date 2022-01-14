package com.jpa.basic.test.proxy;

public class Developer implements DeveloperInterface{

    @Override
    public void develop() {
        System.out.println("오늘도 개발공부하며 무럭무럭 성장하는중!");
    }
}
