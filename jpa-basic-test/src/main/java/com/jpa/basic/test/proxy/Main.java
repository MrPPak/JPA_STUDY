package com.jpa.basic.test.proxy;

public class Main {

    public static void main(String[] args) {

        DeveloperInterface developer1 = new DeveloperInterfaceProxy(new Developer());
        DeveloperInterface developer2 = new DeveloperInheritanceProxy();

        System.out.println("========================================");
        developer1.develop();
        System.out.println("========================================");

        System.out.println("========================================");
        developer2.develop();
        System.out.println("========================================");
    }
}
