package com.xzh.weixin.web.dao;


import com.xzh.weixin.web.dao.model.ResourceModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * @author weiawang 2017年11月27日15:57:00
 */


public interface ResourceMapper {


    @Select({
            "select * from book_w_resource",
            " order by view_count desc limit 4 "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> recommond();

    @Select({
            "select * from book_w_resource",
            "where id = #{id,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    ResourceModel selectById(@Param("id") long id);


    @Select({
            "select * from book_w_resource",
            "where author like  CONCAT('%','#{author,jdbcType=VARCHAR}','%' )  or title like  CONCAT('%','#{title,jdbcType=BIGINT}','%' )   "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> selectByTitleOrAuthor(@Param("title") String title);

    @Select({
            "select * from book_w_resource",
            "where category_id = #{categoryId,jdbcType=BIGINT} and  ( author like  CONCAT('%','#{author,jdbcType=VARCHAR}','%' )  or title like  CONCAT('%','#{title,jdbcType=BIGINT}','%' ))"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> selectByTitleAndCid(@Param("title") String title, @Param("categoryId") long categoryId);

    @Select({
            "select * from book_w_resource",
            "where uid = #{uid,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> selectByUid(@Param("uid") String uid);

    @Select({
            "select * from book_w_resource",
            "where category_id = #{categoryId,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> selectByCategoryId(@Param("categoryId") long categoryId);

    @Insert({
            "insert into book_w_resource (rid, uid, author, title, summary, type, image_id, category_id)",
            "values (#{rid,jdbcType=VARCHAR}, #{uid,jdbcType=VARCHAR}, #{author,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{summary,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{imageId,jdbcType=VARCHAR}, #{categoryId,jdbcType=BIGINT} )"
    })
    int insert(ResourceModel resourceModel);

    @Update({
            "update book_w_resource set",
            "agree_count = agree_count + 1 ",
            "where id = #{id,jdbcType=BIGINT} "
    })
    int updateAgree(@Param("id") long id);

    @Update({
            "update book_w_resource set",
            "view_count = view_count + 1 ",
            "where id = #{id,jdbcType=BIGINT} "
    })
    int updateView(@Param("id") long id);

    @Update({
            "update book_w_resource set",
            "shelf_count = shelf_count + 1 ",
            "where id = #{id,jdbcType=BIGINT} "
    })
    int updateShelf(@Param("id") long id);
}
