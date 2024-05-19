package com.jobposting.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Test {
    @Id
    private int id;
    private String testText;

    public Test() {
    }

    public Test(int id, String testText) {
        this.id = id;
        this.testText = testText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestText() {
        return testText;
    }

    public void setTestText(String testText) {
        this.testText = testText;
    }
}
