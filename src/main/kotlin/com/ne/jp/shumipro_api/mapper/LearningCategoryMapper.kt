package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.entity.LearningCategory
import org.apache.ibatis.annotations.*

@Mapper
interface LearningCategoryMapper {

    @Select("SELECT * FROM learning_category WHERE username = #{username} ORDER BY id")
    fun getByUsername(@Param("username") username: String): List<LearningCategory>

    @Insert("INSERT INTO learning_category (username, name) " +
            "VALUES(#{username}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun register(learningCategory: LearningCategory)

    @Update("UPDATE learning_category " +
            "SET name = #{name} " +
            "WHERE id = #{id} " +
            "AND username = #{username}")
    fun update(learningCategory: LearningCategory)

    @Delete("DELETE FROM learning_category WHERE id = #{id} AND username = #{username}")
    fun delete(id: Int, username: String): Int

    @Select("SELECT * FROM learning_category WHERE id = #{id}")
    fun getById(@Param("id") id: Int): LearningCategory
}