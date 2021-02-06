package com.ne.jp.shumipro_api.entity

import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.GrantedAuthority

class ShumiproLoginUser: org.springframework.security.core.userdetails.User {

    private var user: User

    fun getUser(): User{
        return user
    }

    constructor (user: User) :super(user.username, user.password, determineRoles(user.adminflg)){
        this.user = user
    }

    companion object {
        private val USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER")
        private val ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER")

        private fun determineRoles(adminFlg: Int?): List<GrantedAuthority> {
            return if (adminFlg != null && adminFlg == 1) ADMIN_ROLES else USER_ROLES
        }
    }
}