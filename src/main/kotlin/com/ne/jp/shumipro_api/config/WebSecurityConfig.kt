package com.ne.jp.shumipro_api.config

import com.ne.jp.shumipro_api.mapper.UserMapper
import com.ne.jp.shumipro_api.security.*
import com.ne.jp.shumipro_api.service.ShumiproUserDetailsService
import com.ne.jp.shumipro_api.service.TaskCommentService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler

import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.core.userdetails.UserDetailsService

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.cors.CorsConfigurationSource
import java.lang.Exception
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AccountStatusUserDetailsChecker

import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService
import org.springframework.core.Ordered

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.CorsFilter
import org.springframework.web.filter.GenericFilterBean
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler

import org.springframework.security.web.authentication.logout.LogoutSuccessHandler

@Configuration
@EnableWebSecurity
class WebSecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var userMapper: UserMapper

    @Value("\${front.origin}")
    private val frontOrigin: String = ""

    @Value("\${security.secret-key}")
    private val secretKey: String = ""

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Override
    override fun configure(http: HttpSecurity){
        http
            .authorizeRequests()
            .mvcMatchers("/api/token").permitAll()
            .mvcMatchers("/api/user/**").hasRole("ADMIN")
            .mvcMatchers("/api/task/**").hasRole("USER")
            .anyRequest().authenticated()
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
                .logoutSuccessHandler(logoutSuccessHandler())
            .and()
            .csrf().disable()
            .addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.cors().configurationSource(corsConfigurationSource())
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.eraseCredentials(true)
            .userDetailsService(shumiproUserDetailsService())
            .passwordEncoder(passwordEncoder())
    }

    @Bean("ShumiproUserDetailsService")
    fun shumiproUserDetailsService(): UserDetailsService? {
        return ShumiproUserDetailsService()
    }

    fun tokenFilter(): GenericFilterBean? {
        return ShumiproTokenFilter(userMapper, secretKey)
    }

    @Override
    override fun configure(web: WebSecurity) {
        web.ignoring().mvcMatchers(HttpMethod.OPTIONS, "/**")
    }

    fun getCorsConfiguration(): CorsConfiguration{
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod("GET")
        corsConfiguration.addAllowedMethod("POST")
        corsConfiguration.addAllowedMethod("PUT")
        corsConfiguration.addAllowedMethod("DELETE")
        corsConfiguration.addAllowedMethod("OPTIONS")

        corsConfiguration.addAllowedOrigin(frontOrigin)
        corsConfiguration.allowCredentials = true
        return corsConfiguration
    }

    @Bean
    fun  corsConfigurationSource():CorsConfigurationSource {

        val corsSource = UrlBasedCorsConfigurationSource()
        corsSource.registerCorsConfiguration("/**", getCorsConfiguration())

        return corsSource
    }

    fun authenticationEntryPoint(): AuthenticationEntryPoint {
        return ShumiproAuthenticationEntryPoint()
    }

    fun accessDeniedHandler(): AccessDeniedHandler {
        return ShumiproAccessDeniedHandler()
    }

    fun authenticationSuccessHandler(): AuthenticationSuccessHandler? {
        return ShumiproAuthenticationSuccessHandler(secretKey)
    }

    fun authenticationFailureHandler(): AuthenticationFailureHandler? {
        return ShumiproAuthenticationFailureHandler()
    }

    fun logoutSuccessHandler(): LogoutSuccessHandler? {
        return HttpStatusReturningLogoutSuccessHandler()
    }
}