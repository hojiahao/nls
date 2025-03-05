package cn.edu.szu.nls.business.controller;

import cn.edu.szu.nls.business.exception.BusinessException;
import cn.edu.szu.nls.business.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理、数据预处理
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    /**
     * 所有异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse<Object> exceptionHandler(Exception e) {
        CommonResponse<Object> commonResponse = new CommonResponse<>();
        log.error("系统异常：", e);
        commonResponse.setSuccess(false);
        commonResponse.setMsg("系统出现异常，请联系管理员。");
        return commonResponse;
    }

    /**
     * 所有业务异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResponse<Object> exceptionHandler(BusinessException e) {
        CommonResponse<Object> commonResponse = new CommonResponse<>();
        log.error("系统异常：", e);
        commonResponse.setSuccess(false);
        commonResponse.setMsg(e.getExceptionEnum().getDescription());
        return commonResponse;
    }

    /**
     * 所有业务异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResponse<Object> exceptionHandler(BindException e) {
        CommonResponse<Object> commonResponse = new CommonResponse<>();
        log.error("校验异常：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        commonResponse.setSuccess(false);
        commonResponse.setMsg(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return commonResponse;
    }
}
