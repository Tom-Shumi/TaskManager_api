package com.ne.jp.shumipro_api.service

import com.ne.jp.shumipro_api.dto.SessionBean
import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class ShumiproUserDetailsService: AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Autowired
    lateinit var userMapper: UserMapper

    override fun loadUserDetails(token: PreAuthenticatedAuthenticationToken): UserDetails {
        val credential: HttpServletRequest = token.credentials as HttpServletRequest;

        if (Objects.isNull(credential) || !credential.isRequestedSessionIdValid) {
            throw UsernameNotFoundException("Authorization header must not be empty.")
        }

        val sessionBean: SessionBean = credential.getSession(false).getAttribute("scopedTarget.sessionBean") as SessionBean

        return ShumiproLoginUser(sessionBean.user!!)
    }
}