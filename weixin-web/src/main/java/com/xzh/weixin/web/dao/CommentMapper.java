package com.xzh.weixin.web.dao;

import com.xzh.weixin.web.dao.model.CommentModel;
import com.xzh.weixin.web.dao.model.UserModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * @author xuzh
 * @version 2018年4月21日14:59:42
 */

public interface CommentMapper {


    @Update({
            "update comment set",
            "agree_count = agree_count + 1 ",
            "where cmid = #{cmid,jdbcType=BIGINT} "
    })
    int updateAgree(@Param("cmid") long cmid);

    @Update({
            "update comment set",
            "tread_count = tread_count + 1 ",
            "where cmid = #{cmid,jdbcType=BIGINT} "
    })
    int updateTread(@Param("cmid") long cmid);

    @Select({
            "select * from comment",
            "where rid = #{rid,jdbcType=VARCHAR} order by create_time asc"
    })
    @Results({
            @Result(column = "cmid", property = "cmid", jdbcType = JdbcType.BIGINT),
            @Result(column = "from_uid", property = "fromUid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "to_uid", property = "toUid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "tread_count", property = "treadCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER)
    })
    List<CommentModel> selectByRid(@Param("rid") long rid);

    @Insert({
            "insert into comment (from_uid, to_uid, rid, content )",
            "values (#{fromUid,jdbcType=VARCHAR}, #{toUid,jdbcType=VARCHAR}, #{rid,jdbcType=BIGINT}, #{content,jdbcType=VARCHAR})"
    })
    int insert(CommentModel commentModel);
}
