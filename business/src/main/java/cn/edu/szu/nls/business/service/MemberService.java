package cn.edu.szu.nls.business.service;

import cn.edu.szu.nls.business.domain.Member;
import cn.edu.szu.nls.business.domain.MemberExample;
import cn.edu.szu.nls.business.exception.BusinessException;
import cn.edu.szu.nls.business.exception.BusinessExceptionEnum;
import cn.edu.szu.nls.business.mapper.MemberMapper;
import cn.edu.szu.nls.business.request.MemberRegisterRequest;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * 注册
     */
    public void register(MemberRegisterRequest request) {
        Date now = new Date();
        String mobile = request.getMobile();
        Member memberDB = selectByMobile(request.getMobile());
        if (memberDB != null) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_HAD_REGISTER);
        }
        Member member = new Member();
        member.setId(IdUtil.getSnowflakeNextId());
        member.setMobile(request.getMobile());
        member.setPassword(request.getPassword());
        member.setName(mobile.substring(0, 3) + "****" + mobile.substring(7));
        member.setCreatedAt(now);
        member.setUpdatedAt(now);
        memberMapper.insert(member);
    }
}
