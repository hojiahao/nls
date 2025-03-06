package cn.edu.szu.nls.business.mapper;

import cn.edu.szu.nls.business.domain.SmsCode;
import cn.edu.szu.nls.business.domain.SmsCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsCodeMapper {
    long countByExample(SmsCodeExample example);

    int deleteByExample(SmsCodeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsCode record);

    int insertSelective(SmsCode record);

    List<SmsCode> selectByExample(SmsCodeExample example);

    SmsCode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsCode record, @Param("example") SmsCodeExample example);

    int updateByExample(@Param("record") SmsCode record, @Param("example") SmsCodeExample example);

    int updateByPrimaryKeySelective(SmsCode record);

    int updateByPrimaryKey(SmsCode record);
}