package cn.edu.szu.nls.business.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// @Data的作用是提供类的get、set、equals、hashCode、canEqual、toString方法
@Data
public class SmsCodeRegisterRequest {
    @NotBlank(message = "【手机号】不能为空！")
    private String mobile;
}
