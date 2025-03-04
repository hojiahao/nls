package cn.edu.szu.business.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {
    /**
     * 定义一个切点
     */
    @Pointcut("execution(public * cn.edu.szu..*Controller.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("Before method invoked");
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Around method invoked");
        Object result = joinPoint.proceed();
        log.info("Around method returned");
        return result;
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("After method invoked");
    }
}
