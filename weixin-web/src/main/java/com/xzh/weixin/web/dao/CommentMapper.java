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
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER)
    })
    List<CommentModel> selectByRid(@Param("rid") long rid);

    @Insert({
            "insert into user (from_uid, to_uid, rid, content )",
            "values (#{uid,jdbcType=VARCHAR}, #{nick,jdbcType=VARCHAR}, #{head,jdbcType=BIGINT}, #{coin,jdbcType=VARCHAR})"
    })
    int insert(CommentModel commentModel);
}
