package cn.edu.szu.nls.business.controller.web;

import cn.edu.szu.nls.business.enums.SmsCodeUseEnum;
import cn.edu.szu.nls.business.request.SmsCodeRegisterRequest;
import cn.edu.szu.nls.business.response.CommonResponse;
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

    @PostMapping("/send-for-register")
    public CommonResponse<Object> sendForRegister(@Valid @RequestBody SmsCodeRegisterRequest request) {
        smsCodeService.sendCode(request.getMobile(), SmsCodeUseEnum.REGISTER.getCode());
        return new CommonResponse<>();
    }
}
