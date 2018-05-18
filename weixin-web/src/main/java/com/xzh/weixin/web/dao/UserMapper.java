package com.xzh.weixin.web.dao;

import com.xzh.weixin.web.dao.model.UserModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * @author xuzh
 * @version 2018年3月21日19:59:42
 */

public interface UserMapper {


    @Select({
            "select * from user",
            "where uid = #{uid,jdbcType=VARCHAR} "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "nick", property = "nick", jdbcType = JdbcType.VARCHAR),
            @Result(column = "head", property = "head", jdbcType = JdbcType.VARCHAR),
            @Result(column = "coin", property = "coin", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER)
    })
    UserModel selectByUid(@Param("uid") String uid);

    @Insert({
            "insert into user (uid, nick, head, coin )",
            "values (#{uid,jdbcType=VARCHAR}, #{nick,jdbcType=VARCHAR}, #{head,jdbcType=VARCHAR}, #{coin,jdbcType=INTEGER})"
    })
    int insert(UserModel userModel);

    @Update({
            "update user set",
            "coin = coin + #{addCoin,jdbcType=INTEGER} ",
            "where uid = #{uid,jdbcType=VARCHAR}  "
    })
    int addCoin(@Param("uid") String uid, @Param("addCoin") Integer addCoin);


    @Update({
            "update user set",
            "head = #{head,jdbcType=INTEGER} , ",
            "nick = #{nick,jdbcType=INTEGER} ",
            "where uid = #{uid,jdbcType=VARCHAR}  "
    })
    int updateUser(@Param("uid") String uid, @Param("head") String head, @Param("nick") String nick);

}
