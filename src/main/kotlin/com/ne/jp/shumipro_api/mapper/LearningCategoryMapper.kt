package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.entity.LearningCategory
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface LearningCategoryMapper {

    @Select("SELECT * FROM learning_category WHERE username = #{username} ORDER BY id DESC")
    fun getByUsername(@Param("username") username: String): List<LearningCategory>
}