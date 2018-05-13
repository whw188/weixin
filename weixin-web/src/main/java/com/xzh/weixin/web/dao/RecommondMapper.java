package com.xzh.weixin.web.dao;

import com.xzh.weixin.web.dao.model.RecomondModel;
import com.xzh.weixin.web.dao.model.ShelfModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * @author xuzh
 * @version 2018年3月21日19:59:42
 */

public interface RecommondMapper {


    @Select({
            "select * from recommond",
            "where uid = #{uid,jdbcType=VARCHAR} "
    })
    @Results({
            @Result(column = "rmid", property = "rmid", jdbcType = JdbcType.BIGINT),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "search", property = "search", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<RecomondModel> selectByUid(@Param("uid") String uid);


    @Insert({
            "insert into recommond (uid, search )",
            "values (#{uid,jdbcType=VARCHAR} , #{search,jdbcType=VARCHAR})"
    })
    int insert(RecomondModel recomondModel);


    @Update({
            "update recommond set",
            "search = #{search,jdbcType=VARCHAR} ",
            "where uid = #{uid,jdbcType=VARCHAR}"
    })
    int update(@Param("uid") String uid, @Param("search") String search);
}
