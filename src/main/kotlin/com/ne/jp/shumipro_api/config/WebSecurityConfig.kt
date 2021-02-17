package com.ne.jp.shumipro_api.config

import com.ne.jp.shumipro_api.security.ShumiproAccessDeniedHandler
import com.ne.jp.shumipro_api.security.ShumiproAuthenticationEntryPoint
import com.ne.jp.shumipro_api.security.ShumiproAuthenticationFailureHandler
import com.ne.jp.shumipro_api.security.ShumiproAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler

import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler

import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.core.userdetails.UserDetailsService

import org.springframework.beans.factory.annotation.Qualifier

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder

import org.springframework.beans.factory.annotation.Autowired
import java.lang.Exception


@Configuration
@EnableWebSecurity
class WebSecurityConfig: WebSecurityConfigurerAdapter() {


    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Override
    override fun configure(http: HttpSecurity){
        http
            .authorizeRequests()
            .mvcMatchers("/prelogin")
                .permitAll()
            .mvcMatchers("/api/user/**")
                .hasRole("ADMIN")
            .mvcMatchers("/api/task/**")
                .hasRole("USER")
            .anyRequest()
                .authenticated()
            .and()
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
            .and()
            .formLogin()
                .loginProcessingUrl("/login").permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
            .and()
            .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler())
            .and()
            .csrf()
                //.ignoringAntMatchers("/login")
                .csrfTokenRepository(CookieCsrfTokenRepository())
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(
        auth: AuthenticationManagerBuilder,
        userDetailsService: UserDetailsService,
        passwordEncoder: PasswordEncoder?
    ) {
        auth.eraseCredentials(true)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
    }

    fun authenticationEntryPoint(): AuthenticationEntryPoint {
        return ShumiproAuthenticationEntryPoint()
    }

    fun accessDeniedHandler(): AccessDeniedHandler {
        return ShumiproAccessDeniedHandler()
    }

    fun authenticationSuccessHandler(): AuthenticationSuccessHandler {
        return ShumiproAuthenticationSuccessHandler()
    }

    fun authenticationFailureHandler(): AuthenticationFailureHandler {
        return ShumiproAuthenticationFailureHandler()
    }

    fun logoutSuccessHandler(): LogoutSuccessHandler {
        return HttpStatusReturningLogoutSuccessHandler()
    }
}