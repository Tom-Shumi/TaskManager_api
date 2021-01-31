package com.ne.jp.shumipro_api.mapper

import com.ne.jp.shumipro_api.entity.User
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserMapper {

    /**
     * ユーザ取得
     */
    fun getUser(username: String): User

    /**
     * ユーザ登録
     */
    fun insertUser(user: User)
}