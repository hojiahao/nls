package cn.edu.szu.nls.business.service;

import cn.edu.szu.nls.business.domain.SmsCode;
import cn.edu.szu.nls.business.enums.SmsCodeStatusEnum;
import cn.edu.szu.nls.business.mapper.SmsCodeMapper;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SmsCodeService {
    @Resource
    private SmsCodeMapper smsCodeMapper;
    /**
     * 发送短信验证码
     * @param mobile    手机号
     * @param use   用途
     */
    public void sendCode(String mobile, String use) {
        Date now = new Date();
        String code = RandomUtil.randomNumbers(6);
        // 保存验证码到数据库
        SmsCode smsCode = new SmsCode();
        smsCode.setId(IdUtil.getSnowflakeNextId());
        smsCode.setMobile(mobile);
        smsCode.setCode(code);
        smsCode.setUse(use);
        smsCode.setStatus(SmsCodeStatusEnum.NOT_USED.getCode());
        smsCode.setCreatedAt(now);
        smsCode.setUpdatedAt(now);
        smsCodeMapper.insert(smsCode);
        // 对接短信通道，发送短信
    }
}
