package cn.edu.szu.business.service;

import cn.edu.szu.business.mapper.DemoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Resource
    private DemoMapper  demoMapper;

    public int count() {
       return demoMapper.count();
    }
}
