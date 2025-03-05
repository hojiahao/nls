package cn.edu.szu.nls.business.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private BusinessExceptionEnum exceptionEnum;

    /**
     * 不写入堆栈信息，简化异常日志，提高性能
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
