package com.xzh.weixin.web.dao;

import com.xzh.weixin.web.dao.model.UserSignModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * @author xuzh
 * @version 2018年4月22日19:39:41
 */

public interface UserSignMapper {

    @Select({
            "select * from user_sign",
            "where uid = #{uid,jdbcType=VARCHAR}  order by create_time desc limit 1"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER)
    })
    List<UserSignModel> selectByUid(@Param("uid") String uid);

    @Insert({
            "insert into user_sign (uid )",
            "values (#{uid,jdbcType=VARCHAR} )"
    })
    int insert(UserSignModel userSignModel);

}
