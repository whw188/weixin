package com.xzh.weixin.web.dao;


import com.xzh.weixin.web.dao.model.ResourceModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * @author xuzh
 * @version  2018年3月21日19:59:42
 */


public interface ResourceMapper {


    @Select({
            "select * from resource",
            "where status = 1",
            "order by view_count desc limit 4 "
    })
    @Results({
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "file_id", property = "fileId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cid", property = "cid", jdbcType = JdbcType.BIGINT),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> recommond();

    @Select({
            "select * from resource",
            "where  rid = #{rid,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "file_id", property = "fileId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cid", property = "cid", jdbcType = JdbcType.BIGINT),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    ResourceModel downloadRid(@Param("rid") long rid);

    @Select({
            "select * from resource",
            "where status = 1",
            "and rid = #{rid,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "file_id", property = "fileId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cid", property = "cid", jdbcType = JdbcType.BIGINT),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    ResourceModel selectById(@Param("rid") long rid);

    @Select({
            "select * from resource",
            "where status = #{status,jdbcType=INTEGER} "
    })
    @Results({
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "file_id", property = "fileId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cid", property = "cid", jdbcType = JdbcType.BIGINT),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> selectByStatus(@Param("status") int status);


    @Select({
            "select * from resource",
            "where status = #{status,jdbcType=INTEGER} and type = #{type,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "file_id", property = "fileId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cid", property = "cid", jdbcType = JdbcType.BIGINT),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> selectByStatusAndType(@Param("status") int status,@Param("type") String type);



    @Select({
            "select * from resource",
            "where status = 1 and ( author like  CONCAT('%','${author}','%' )  or title like  CONCAT('%','${title}','%' ))  "
    })
    @Results({
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "file_id", property = "fileId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cid", property = "cid", jdbcType = JdbcType.BIGINT),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> selectByTitleOrAuthor(@Param("title") String title, @Param("author") String author);

    @Select({
            "select * from resource",
            "where  status = 1 and  cid = #{cid,jdbcType=BIGINT} and  ( author like  CONCAT('%','${author}','%' )  or title like  CONCAT('%','${title}','%' ))"
    })
    @Results({
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "file_id", property = "fileId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cid", property = "cid", jdbcType = JdbcType.BIGINT),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> selectByTitleAndCid(@Param("title") String title, @Param("author") String author, @Param("cid") long cid);

    @Select({
            "select * from resource",
            "where status = 1 and uid = #{uid,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "file_id", property = "fileId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cid", property = "cid", jdbcType = JdbcType.BIGINT),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> selectByUid(@Param("uid") String uid);

    @Select({
            "select * from resource",
            "where status = 1 and cid = #{cid,jdbcType=BIGINT} and type = #{type,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "file_id", property = "fileId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cid", property = "cid", jdbcType = JdbcType.BIGINT),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> selectByCidAndType(@Param("cid") long cid,@Param("type") String type);


    @Select({
            "select * from resource",
            "where status = 1 and cid = #{cid,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column = "rid", property = "rid", jdbcType = JdbcType.BIGINT),
            @Result(column = "file_id", property = "fileId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "uid", property = "uid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_id", property = "imageId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cid", property = "cid", jdbcType = JdbcType.BIGINT),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "view_count", property = "viewCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "agree_count", property = "agreeCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "shelf_count", property = "shelfCount", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<ResourceModel> selectByCid(@Param("cid") long cid);

    @Insert({
            "insert into resource (file_id, file_name, uid, author, title, summary, type, image_id, cid, price ,status)",
            "values (#{fileId,jdbcType=VARCHAR},  #{fileName,jdbcType=VARCHAR}, #{uid,jdbcType=VARCHAR}, #{author,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{summary,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{imageId,jdbcType=VARCHAR}, #{cid,jdbcType=BIGINT}, #{price,jdbcType=INTEGER}, #{status,jdbcType=INTEGER} )"
    })
    int insert(ResourceModel resourceModel);

    @Update({
            "update resource set",
            "agree_count = agree_count + 1 ",
            "where rid = #{rid,jdbcType=BIGINT} "
    })
    int updateAgree(@Param("rid") long rid);

    @Update({
            "update resource set",
            "view_count = view_count + 1 ",
            "where rid = #{rid,jdbcType=BIGINT} "
    })
    int updateView(@Param("rid") long rid);

    @Update({
            "update resource set",
            "shelf_count = shelf_count + 1 ",
            "where rid = #{rid,jdbcType=BIGINT} "
    })
    int updateShelf(@Param("rid") long rid);

    @Update({
            "update resource set",
            "status = #{status,jdbcType=INTEGER} ",
            "where rid = #{rid,jdbcType=BIGINT} "
    })
    int updateStatus(@Param("status") int status, @Param("rid") long rid);
}
