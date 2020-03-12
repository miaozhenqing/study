package com.example.qzm.study.function.test.reflex;

/**
 * @ClassName User
 * @Description TODO
 * @Version 1.0
 **/
public class User {
    private String name;
    private int count;

    public User() {
    }

    public User(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public void increCount() {
        this.count += 1;
    }

    @Override
    public String toString() {
        return "name=" + name + " " + "count=" + count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
