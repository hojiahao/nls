package cn.edu.szu.nls.business.controller.web;

import cn.edu.szu.nls.business.enums.SmsCodeUseEnum;
import cn.edu.szu.nls.business.request.MemberRegisterRequest;
import cn.edu.szu.nls.business.response.CommonResponse;
import cn.edu.szu.nls.business.service.MemberService;
import cn.edu.szu.nls.business.service.SmsCodeService;
import cn.hutool.crypto.digest.DigestUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/web/member")
public class WebMemberController {

    @Resource
    private MemberService memberService;

    @Resource
    private SmsCodeService smsCodeService;

    @PostMapping("/register")
    public CommonResponse<Object> register(@Valid @RequestBody MemberRegisterRequest request) {
        log.info("会员注册开始：{}", request.getMobile());
        request.setPassword(DigestUtil.md5Hex(request.getPassword()));
        // 校验验证码是否有效
        smsCodeService.validateCode(request.getMobile(), SmsCodeUseEnum.REGISTER.getCode(), request.getCode());
        log.info("用户{}输入的验证码有效。", request.getMobile());
        memberService.register(request);
        return new CommonResponse<>();
    }
}
