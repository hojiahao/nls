package cn.edu.szu.nls.business.controller.web;

import cn.edu.szu.nls.business.request.SmsCodeRegisterRequest;
import cn.edu.szu.nls.business.response.CommonResponse;
import cn.edu.szu.nls.business.service.KaptchaService;
import cn.edu.szu.nls.business.service.SmsCodeService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/sms-code")
public class WebSmsCodeController {

    @Resource
    private SmsCodeService smsCodeService;

    @Resource
    private KaptchaService kaptchaService;

    @PostMapping("/send-for-register")
    public CommonResponse<Object> sendForRegister(@Valid @RequestBody SmsCodeRegisterRequest request) {
        // 校验图片验证码，防止短信攻击，不加的话，只能防止同一手机攻击，加上图片验证码，可防止不同的手机号攻击
        kaptchaService.validCode(request.getImageCode(), request.getImageCodeToken());
        smsCodeService.sendCodeForRegister(request.getMobile());
        return new CommonResponse<>();
    }
}
