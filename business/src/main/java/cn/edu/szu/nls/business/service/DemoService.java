package cn.edu.szu.nls.business.service;

import cn.edu.szu.nls.business.mapper.custom.CustomizedDemoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Resource
    private CustomizedDemoMapper customizedDemoMapper;

    public int count() {
       return customizedDemoMapper.count();
    }
}
