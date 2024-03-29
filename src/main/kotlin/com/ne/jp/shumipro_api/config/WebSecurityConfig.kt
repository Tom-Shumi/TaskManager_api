package com.ne.jp.shumipro_api.config

import com.ne.jp.shumipro_api.Constants
import com.ne.jp.shumipro_api.Constants.Companion.CREATE_USER_PATH
import com.ne.jp.shumipro_api.mapper.UserMapper
import com.ne.jp.shumipro_api.security.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.http.HttpMethod

import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.http.SessionCreationPolicy

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
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

    @Value("\${allow.front.origin}")
    private val FRONT_ORIGIN: String = ""

    @Value("\${security.secret-key}")
    private val SECRET_KEY: String = ""

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Override
    override fun configure(http: HttpSecurity){
        http
            .authorizeRequests()
            .mvcMatchers(CREATE_USER_PATH).permitAll()
//            .mvcMatchers("/graphql").permitAll() // TODO
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
            .and()
            .formLogin()
                .loginProcessingUrl(Constants.LOGIN_PATH).permitAll()
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

    fun tokenFilter(): GenericFilterBean? {
        return ShumiproTokenFilter(userMapper, SECRET_KEY)
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

        corsConfiguration.addAllowedOrigin(FRONT_ORIGIN)
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
        return ShumiproAuthenticationSuccessHandler(SECRET_KEY)
    }

    fun authenticationFailureHandler(): AuthenticationFailureHandler? {
        return ShumiproAuthenticationFailureHandler()
    }

    fun logoutSuccessHandler(): LogoutSuccessHandler? {
        return HttpStatusReturningLogoutSuccessHandler()
    }
}