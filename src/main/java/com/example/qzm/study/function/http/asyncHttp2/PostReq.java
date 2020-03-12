package com.example.qzm.study.function.http.asyncHttp2;

/**
 * @ClassName PostReq
 * @Description TODO
 * @Version 1.0
 **/
public class PostReq {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toPostReqString() {
        return "name=" + name +
                ",age=" + age;
    }
}
