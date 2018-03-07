package com.xzh.weixin.web.dao;

import com.xzh.weixin.web.dao.model.ResourceModel;
import com.xzh.weixin.web.dao.model.ShelfModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * @author weiawang 2017年11月27日15:57:00
 */

public interface ShelfMapper {


    @Select({
            "select * from shelf",
            "where uid = #{uid,jdbcType=VARCHAR} "
    })
    @Results({
            @Result(column = "sid", property = "sid", jdbcType = JdbcType.BIGINT),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "shelf", property = "shelf", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree", property = "agree", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ShelfModel> selectByUid(@Param("uid") String uid);

    @Select({
            "select * from shelf",
            "where uid = #{uid,jdbcType=VARCHAR} and rid = #{rid,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column = "sid", property = "sid", jdbcType = JdbcType.BIGINT),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "shelf", property = "shelf", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree", property = "agree", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
     ShelfModel  selectByUidAndRid(@Param("uid") String uid, @Param("rid") long rid);

    @Insert({
            "insert into shelf (uid, rid, agree, shelf )",
            "values (#{uid,jdbcType=VARCHAR}, #{rid,jdbcType=BIGINT}, #{agree,jdbcType=INTEGER}, #{shelf,jdbcType=INTEGER})"
    })
    int insert(ShelfModel shelfModel);

    @Update({
            "update shelf set",
            "agree = #{agree,jdbcType=INTEGER} ",
            "where uid = #{uid,jdbcType=VARCHAR} and rid = #{rid,jdbcType=BIGINT}"
    })
    int updateAgree(@Param("uid") String uid, @Param("rid") long rid, @Param("agree") int agree);

    @Update({
            "update shelf set",
            "shelf = #{shelf,jdbcType=INTEGER} ",
            "where uid = #{uid,jdbcType=VARCHAR} and rid = #{rid,jdbcType=BIGINT}"
    })
    int updateShelf(@Param("uid") String uid, @Param("rid") long rid, @Param("shelf") int shelf);
}
