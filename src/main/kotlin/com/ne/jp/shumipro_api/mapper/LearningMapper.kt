package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.dto.LearningInfoDto
import com.ne.jp.shumipro_api.entity.Learning
import org.apache.ibatis.annotations.*

@Mapper
interface LearningMapper {

    fun listLearningInfo(@Param("username") username: String, @Param("search") search: String?, @Param("categoryId") categoryId: Int?,
                              @Param("nextKey") nextKey: Int?, @Param("limit") limit: Int): List<LearningInfoDto>

    @Insert("INSERT INTO learning (username, category_id, content, reference_url, create_date) " +
            "VALUES(#{username}, #{categoryId}, #{content}, #{referenceUrl}, now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun register(learning: Learning)

    @Select("SELECT l.*, lc.name categoryName " +
            "FROM learning l " +
            "LEFT JOIN learning_category lc ON l.category_Id = lc.id " +
            "WHERE l.id = #{id}")
    fun getById(@Param("id") id: Int): LearningInfoDto
}