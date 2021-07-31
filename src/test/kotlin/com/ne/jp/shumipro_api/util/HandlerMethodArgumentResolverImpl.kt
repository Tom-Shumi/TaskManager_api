package com.ne.jp.shumipro_api.util

import com.ne.jp.shumipro_api.entity.ShumiproLoginUser
import com.ne.jp.shumipro_api.entity.User
import org.springframework.core.MethodParameter
import org.springframework.lang.Nullable
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class HandlerMethodArgumentResolverImpl: HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter : MethodParameter): Boolean {
        return parameter.parameterType.isAssignableFrom(ShumiproLoginUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        @Nullable mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        @Nullable binderFactory: WebDataBinderFactory?
    ): Any {
        return ShumiproLoginUser(User("shumiya", "shumiya", 1, 1))
    }

}