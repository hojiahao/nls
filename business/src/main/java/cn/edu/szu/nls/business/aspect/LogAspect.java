package cn.edu.szu.nls.business.aspect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

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
//        log.info("Before method invoked");
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("------------ Around method invoked ------------");
        long startTime = System.currentTimeMillis();
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        // 打印请求信息
        log.info("Request Address: {} {}", request.getRequestURI(), request.getMethod());
        log.info("Class And Function: {}.{}", signature.getDeclaringTypeName(), name);
        log.info("Remote Address: {}", request.getRemoteAddr());

        // 打印请求参数
        Object[] args = joinPoint.getArgs();
        // log.info("Request Args: {}", JSONObject.toJSONString(args));

        // 排除特殊类型的参数，如文件类型
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }

        // 排除敏感字段，敏感字段或太长的字段不显示，如身份证号、手机号、邮箱和密码等
        String[] excludeProperties = {"cvv2", "idCard"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        log.info("Request Arguments: {}", JSONObject.toJSONString(excludeFilter));
        Object result = joinPoint.proceed();
        log.info("Request Result: {}", JSONObject.toJSONString(result, excludeFilter));
        log.info("------------ Around Method finished, Total Time: {} ms ------------", System.currentTimeMillis() - startTime);
        return result;
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
//        log.info("After method invoked");
    }
}
