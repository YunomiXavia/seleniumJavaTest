package com.automationtesting;

import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TestNG testng = new TestNG();

        List<Class<?>> testClasses = new ArrayList<>();
        testClasses.add(com.automationtesting.tests.RegisterTest.class);
        testClasses.add(com.automationtesting.tests.LoginTest.class);

        testng.setTestClasses(testClasses.toArray(new Class[0]));

        testng.run();
    }
}
