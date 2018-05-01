package com.xzh.weixin.web.dao;

import com.xzh.weixin.web.dao.model.BarrageModel;
import com.xzh.weixin.web.dao.model.ShelfModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * @author xuzh
 * @version  2018年3月21日19:59:42
 */

public interface BarrageMapper {


    @Select({
            "select * from barrage",
            "where rid = #{rid,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column = "bid", property = "bid", jdbcType = JdbcType.BIGINT),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "text", property = "text", jdbcType = JdbcType.VARCHAR),
            @Result(column = "color", property = "color", jdbcType = JdbcType.VARCHAR),
            @Result(column = "time", property = "time", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<BarrageModel> selectByRid(@Param("rid") Long rid);



    @Insert({
            "insert into barrage (uid, rid, text, color, time )",
            "values (#{uid,jdbcType=VARCHAR}, #{rid,jdbcType=BIGINT}, #{text,jdbcType=VARCHAR}, #{color,jdbcType=VARCHAR}, #{time,jdbcType=INTEGER})"
    })
    int insert(BarrageModel barrageModel);


}
