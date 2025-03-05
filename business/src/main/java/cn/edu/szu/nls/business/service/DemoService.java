package cn.edu.szu.nls.business.service;

import cn.edu.szu.nls.business.domain.Demo;
import cn.edu.szu.nls.business.domain.DemoExample;
import cn.edu.szu.nls.business.mapper.DemoMapper;
import cn.edu.szu.nls.business.mapper.custom.CustomizedDemoMapper;
import cn.edu.szu.nls.business.request.DemoQueryRequest;
import cn.edu.szu.nls.business.response.DemoQueryResponse;
import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    @Resource
    private CustomizedDemoMapper customizedDemoMapper;

    @Resource
    private DemoMapper demoMapper;

    public int count() {
        return customizedDemoMapper.count();
        // return Math.toIntExact(demoMapper.countByExample(null));
    }

    public List<DemoQueryResponse> query(DemoQueryRequest request) {
        String mobile = request.getMobile();
        DemoExample demoExample = new DemoExample();
        demoExample.setOrderByClause("id desc");
        DemoExample.Criteria criteria = demoExample.createCriteria();
        if (mobile != null) {
            criteria.andMobileEqualTo(mobile);
        }
        List<Demo> list = demoMapper.selectByExample(demoExample);
        return BeanUtil.copyToList(list, DemoQueryResponse.class);
    }
}
