package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.dto.LearningInfoDto
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface LearningMapper {

    @Select("SELECT l.*, lc.name categoryName " +
            "FROM learning l " +
            "LEFT JOIN learning_category lc ON l.category_Id = lc.id " +
            "WHERE l.username = #{username} " +
            "ORDER BY create_date DESC")
    fun getLearningByUsername(@Param("username") username: String): List<LearningInfoDto>
}