package com.ne.jp.shumipro_api.config

import com.ne.jp.shumipro_api.security.ApiPreAuthenticatedProcessingFilter
import com.ne.jp.shumipro_api.security.ShumiproAccessDeniedHandler
import com.ne.jp.shumipro_api.security.ShumiproAuthenticationEntryPoint
import com.ne.jp.shumipro_api.service.ShumiproUserDetailsService
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


@Configuration
@EnableWebSecurity
class WebSecurityConfig: WebSecurityConfigurerAdapter() {

    @Value("\${front.origin}")
    private val frontOrigin: String = ""

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
            .addFilter(preAuthenticatedProcessingFilter())
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
            .and()
            .csrf()
                .ignoringAntMatchers("/api/token")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

        http.cors().configurationSource(corsConfigurationSource())
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
    }

    fun  corsConfigurationSource():CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod("GET")
        corsConfiguration.addAllowedMethod("POST")
        corsConfiguration.addAllowedMethod("PUT")
        corsConfiguration.addAllowedMethod("DELETE")
        corsConfiguration.addAllowedMethod("OPTIONS")

        corsConfiguration.addAllowedOrigin(frontOrigin)
        corsConfiguration.allowCredentials = true

        val corsSource = UrlBasedCorsConfigurationSource()
        corsSource.registerCorsConfiguration("/**", corsConfiguration)

        return corsSource
    }

    @Bean
    fun authenticationUserDetailsService(): AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
        return ShumiproUserDetailsService()
    }

    @Bean
    fun preAuthenticationProvider(): PreAuthenticatedAuthenticationProvider {
        val preAuthenticatedAuthenticationProvider = PreAuthenticatedAuthenticationProvider()
        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService())
        preAuthenticatedAuthenticationProvider.setUserDetailsChecker(AccountStatusUserDetailsChecker())
        return preAuthenticatedAuthenticationProvider
    }

    @Bean
    fun preAuthenticatedProcessingFilter(): AbstractPreAuthenticatedProcessingFilter {
        val preAuthenticatedProcessingFilter = ApiPreAuthenticatedProcessingFilter()
        preAuthenticatedProcessingFilter.setAuthenticationManager(authenticationManager())
        return preAuthenticatedProcessingFilter
    }

    fun authenticationEntryPoint(): AuthenticationEntryPoint {
        return ShumiproAuthenticationEntryPoint()
    }

    fun accessDeniedHandler(): AccessDeniedHandler {
        return ShumiproAccessDeniedHandler()
    }
}