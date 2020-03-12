package com.example.qzm.study.function.http.asyncHttp2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @ClassName TestController
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@RequestMapping("/http")
public class MyController {
    @PostConstruct
    public void init() {
        System.out.println("Dddddddddddddd");
    }

    @RequestMapping(value = "/postTest", method = RequestMethod.POST)
    public String testController(@RequestBody PostReq postReq) {
        System.out.println("start：" + postReq.toPostReqString());
        System.out.println("end：" + postReq.toPostReqString());
        return "success!";
    }

    @RequestMapping(value = "/getTest", method = RequestMethod.GET)
    public String testController(String name) {
        System.out.println("name = " + name);
        return "success!";
    }
}
