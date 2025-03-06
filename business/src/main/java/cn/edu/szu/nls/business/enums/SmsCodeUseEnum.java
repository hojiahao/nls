package cn.edu.szu.nls.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SmsCodeUseEnum {
    REGISTER("0", "注册"),
    FORGET_PASSWORD("1", "忘记密码");

    private final String code;

    private final String desc;
}
