package com.xzh.weixin.web.dao;

import com.xzh.weixin.web.dao.model.OrderModel;
import com.xzh.weixin.web.dao.model.UserSignModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * @author xuzh
 * @version 2018年4月22日19:39:41
 */

public interface OrderMapper {

    @Select({
            "select * from user_order ",
            "where uid = #{uid,jdbcType=VARCHAR}   and rid = #{rid,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER)
    })
    List<OrderModel> selectByUid(@Param("uid") String uid, @Param("rid") long rid);

    @Insert({
            "insert into user_order ( uid ,rid ,price ) ",
            "values (#{uid,jdbcType=VARCHAR} , #{rid,jdbcType=BIGINT}, #{price,jdbcType=INTEGER} )"
    })
    int insert(OrderModel orderModel);

}
