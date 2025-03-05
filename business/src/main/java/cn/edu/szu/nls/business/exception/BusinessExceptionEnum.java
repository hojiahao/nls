package cn.edu.szu.nls.business.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum BusinessExceptionEnum {
    DEMO_MOBILE_NOT_NULL("手机号不能为空！！！");
    private final String description;
}
