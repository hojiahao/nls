package cn.edu.szu.nls.business.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// @Data的作用是提供类的get、set、equals、hashCode、canEqual、toString方法
@Data
public class SmsCodeRegisterRequest {
    @NotBlank(message = "【手机号】不能为空！")
    private String mobile;

    /**
     * 验证码
     */
    @NotBlank(message = "【图片验证码】不能为空")
    private String imageCode;

    /**
     * 图片验证码的token
     */
    @NotBlank(message = "【图片验证码】参数非法")
    private String imageCodeToken;
}
