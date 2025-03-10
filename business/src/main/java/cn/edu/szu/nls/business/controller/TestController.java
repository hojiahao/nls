package cn.edu.szu.nls.business.controller;

import cn.edu.szu.nls.business.request.DemoQueryRequest;
import cn.edu.szu.nls.business.response.CommonResponse;
import cn.edu.szu.nls.business.response.DemoQueryResponse;
import cn.edu.szu.nls.business.service.DemoService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Resource
    private DemoService demoService;

    @GetMapping("/hello")
    public CommonResponse<String> hello() {
        return new CommonResponse<>("hello world!");
    }

    @GetMapping("/count")
    public CommonResponse<Integer> count() {
        return new CommonResponse<>(demoService.count());
    }

    @GetMapping("/query")
    public CommonResponse<List<DemoQueryResponse>> query(@Valid DemoQueryRequest request) {
        List<DemoQueryResponse> list = demoService.query(request);
        return new CommonResponse<>(list);
    }
}
