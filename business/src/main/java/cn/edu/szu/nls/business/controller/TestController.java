package cn.edu.szu.nls.business.controller;

import cn.edu.szu.nls.business.service.DemoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
