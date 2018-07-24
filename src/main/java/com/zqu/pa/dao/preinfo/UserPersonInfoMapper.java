package com.zqu.pa.dao.preinfo;

import com.zqu.pa.entity.preinfo.UserPersonInfo;
import com.zqu.pa.entity.preinfo.UserPersonInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPersonInfoMapper {
    long countByExample(UserPersonInfoExample example);

    int deleteByExample(UserPersonInfoExample example);

    int deleteByPrimaryKey(String userId);

    int insert(UserPersonInfo record);

    int insertSelective(UserPersonInfo record);

    List<UserPersonInfo> selectByExample(UserPersonInfoExample example);

    UserPersonInfo selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") UserPersonInfo record, @Param("example") UserPersonInfoExample example);

    int updateByExample(@Param("record") UserPersonInfo record, @Param("example") UserPersonInfoExample example);

    int updateByPrimaryKeySelective(UserPersonInfo record);

    int updateByPrimaryKey(UserPersonInfo record);
}