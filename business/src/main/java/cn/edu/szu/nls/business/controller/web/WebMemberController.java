package cn.edu.szu.nls.business.controller.web;

import cn.edu.szu.nls.business.request.MemberRegisterRequest;
import cn.edu.szu.nls.business.response.CommonResponse;
import cn.edu.szu.nls.business.service.MemberService;
import cn.hutool.crypto.digest.DigestUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/member")
public class WebMemberController {

    @Resource
    private MemberService memberService;

    @PostMapping("/register")
    public CommonResponse<Object> register(@Valid @RequestBody MemberRegisterRequest request) {
        request.setPassword(DigestUtil.md5Hex(request.getPassword()));
        memberService.register(request);
        return new CommonResponse<>();
    }
}
