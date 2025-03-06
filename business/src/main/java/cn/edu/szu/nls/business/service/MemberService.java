package cn.edu.szu.nls.business.service;

import cn.edu.szu.nls.business.domain.Member;
import cn.edu.szu.nls.business.domain.MemberExample;
import cn.edu.szu.nls.business.mapper.MemberMapper;
import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    /**
     * 按手机号查询会员信息
     * @param mobile 手机号
     * @return members 会员信息
     */
    public Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(members)) {
            return members.get(0);
        }
        else {
            return null;
        }
    }
}
