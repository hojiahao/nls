package cn.edu.szu.nls.business.service;

import cn.edu.szu.nls.business.exception.BusinessException;
import cn.edu.szu.nls.business.exception.BusinessExceptionEnum;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class KaptchaService {

    private static final Logger LOG = LoggerFactory.getLogger(KaptchaService.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 校验图片验证码
     * 根据验证码token去获取缓存中的验证码，和用户输入的验证码是否一致
     * @param code 用户输入的验证码
     * @param token 验证码对应的token，用来标识是哪个用户申请的图片验证码
     */
    public void validCode(String code, String token) {
        String imageCode = stringRedisTemplate.opsForValue().get(token);
        LOG.info("从redis中获取到的验证码：{}", imageCode);
        if (ObjectUtils.isEmpty(imageCode)) {
            LOG.warn("验证码校验失败，验证码已过期。");
            throw new BusinessException(BusinessExceptionEnum.IMAGE_CODE_ERROR);
        }
        if (!imageCode.equalsIgnoreCase(code)) {
            LOG.warn("验证码校验失败，验证码不正确。");
            throw new BusinessException(BusinessExceptionEnum.IMAGE_CODE_ERROR);
        } else {
            // 验证通过后，移除验证码
            stringRedisTemplate.delete(token);
        }
    }
}
