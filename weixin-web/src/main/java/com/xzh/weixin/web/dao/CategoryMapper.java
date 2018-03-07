package com.xzh.weixin.web.dao;

import com.xzh.weixin.web.dao.model.CategoryModel;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * @author weiawang 2017年11月27日15:57:00
 */

public interface CategoryMapper {


    @Select({
            "select * from category "
    })
    @Results({
            @Result(column = "cid", property = "cid", jdbcType = JdbcType.BIGINT),
            @Result(column = "cname", property = "cname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "detail", property = "detail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<CategoryModel> selectAll();

}
