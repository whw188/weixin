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
            "select * from book_w_shelf",
            "where uid = #{uid,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column = "uid", property = "id", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "shelf", property = "shelf", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree", property = "agree", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ShelfModel> selectByUid(@Param("uid") String uid);

    @Select({
            "select * from book_w_resource",
            "where uid = #{uid,jdbcType=VARCHAR} and rid = #{rid,jdbcType=VARCHAR} "
    })
    @Results({
            @Result(column = "uid", property = "id", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "shelf", property = "shelf", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree", property = "agree", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ShelfModel> selectByUidAndRid(@Param("uid") String uid, @Param("rid") String rid);

    @Insert({
            "insert into book_w_shelf (uid, rid, agree, shelf )",
            "values (#{uid,jdbcType=VARCHAR}, #{rid,jdbcType=VARCHAR}, #{agree,jdbcType=INTEGER}, #{shelf,jdbcType=INTEGER})"
    })
    int insert(ShelfModel shelfModel);

    @Update({
            "update book_w_shelf set",
            "agree = #{agreeCount,jdbcType=INTEGER} ",
            "where uid = #{uid,jdbcType=BIGINT} and rid = #{rid,jdbcType=BIGINT}"
    })
    int updateAgree(@Param("uid") String uid, @Param("rid") String rid, @Param("agree") int agree);

    @Update({
            "update book_w_resource set",
            "shelf = #{viewCount,jdbcType=INTEGER} ",
            "where uid = #{uid,jdbcType=BIGINT} and rid = #{rid,jdbcType=BIGINT}"
    })
    int updateShelf(@Param("uid") String uid, @Param("rid") String rid, @Param("shelf") int shelf);
}
