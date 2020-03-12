package com.example.qzm.study.function.http.asynchttp1.Controller;

import com.example.qzm.study.function.http.asynchttp1.service.HttpReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestClient
 * @Description TODO
 * @Version 1.0
 **/
@RestController
public class TestClient {
    @Autowired
    private HttpReportService httpReportService;

    @RequestMapping("/doooo")
    public void doooo(int n) {
        int threadNum = 1;
//        for (int i = 0; i < threadNum; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
                    for (int i = 0; i < n; i++) {
                        Map<String, String> map = new HashMap<>();
                        map.put("id", String.valueOf(i));
                        httpReportService.report("http://localhost:10000/http", map, null);
                    }
//                }
//            }).start();
//        }
    }

    @RequestMapping("/http")
    public String test(String id) {
        System.out.println("success.....id=" + id);
        return "success......";
    }
}
