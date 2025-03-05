package cn.edu.szu.nls.business.controller;

import cn.edu.szu.nls.business.domain.Demo;
import cn.edu.szu.nls.business.request.DemoQueryRequest;
import cn.edu.szu.nls.business.service.DemoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Resource
    private DemoService demoService;

    @GetMapping("/hello")
    public String hello() {
        return "hello world!";
    }

    @GetMapping("/count")
    public int count() {
        return demoService.count();
    }

    @GetMapping("/query")
    public List<Demo> query(DemoQueryRequest request) {
        return demoService.query(request);
    }
}
