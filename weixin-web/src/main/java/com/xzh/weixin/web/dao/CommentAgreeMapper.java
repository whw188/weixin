package com.xzh.weixin.web.dao;

import com.xzh.weixin.web.dao.model.CommentAgreeModel;
import com.xzh.weixin.web.dao.model.ShelfModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * @author xuzh
 * @version  2018年3月21日19:59:42
 */

public interface CommentAgreeMapper {


    @Select({
            "select * from comment_agree",
            "where uid = #{uid,jdbcType=VARCHAR} "
    })
    @Results({
            @Result(column = "sid", property = "sid", jdbcType = JdbcType.BIGINT),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cmid", property = "cmid", jdbcType = JdbcType.BIGINT),
            @Result(column = "tread", property = "tread", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree", property = "agree", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<CommentAgreeModel> selectByUid(@Param("uid") String uid);

    @Select({
            "select * from comment_agree",
            "where uid = #{uid,jdbcType=VARCHAR} and cmid = #{cmid,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column = "sid", property = "sid", jdbcType = JdbcType.BIGINT),
            @Result(column = "cmid", property = "cmid", jdbcType = JdbcType.BIGINT),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tread", property = "tread", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree", property = "agree", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    CommentAgreeModel  selectByUidAndCmid(@Param("uid") String uid, @Param("cmid") long cmid);

    @Insert({
            "insert into comment_agree (uid, cmid, agree, tread )",
            "values (#{uid,jdbcType=VARCHAR}, #{cmid,jdbcType=BIGINT}, #{agree,jdbcType=INTEGER}, #{tread,jdbcType=INTEGER})"
    })
    int insert(CommentAgreeModel commentAgreeModel);

    @Update({
            "update comment_agree set",
            "agree = #{agree,jdbcType=INTEGER} ",
            "where uid = #{uid,jdbcType=VARCHAR} and cmid = #{cmid,jdbcType=BIGINT}"
    })
    int updateAgree(@Param("uid") String uid, @Param("cmid") long cmid, @Param("agree") int agree);

    @Update({
            "update comment_agree set",
            "tread = #{tread,jdbcType=INTEGER} ",
            "where uid = #{uid,jdbcType=VARCHAR} and cmid = #{cmid,jdbcType=BIGINT}"
    })
    int updateShelf(@Param("uid") String uid, @Param("cmid") long rid, @Param("tread") int tread);
}
