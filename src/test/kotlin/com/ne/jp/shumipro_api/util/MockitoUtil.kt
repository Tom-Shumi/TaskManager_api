package com.ne.jp.shumipro_api.util

import org.mockito.Mockito

class MockitoUtil {

    companion object {
        fun <T> any(): T {
            return Mockito.any()
        }
    }

}