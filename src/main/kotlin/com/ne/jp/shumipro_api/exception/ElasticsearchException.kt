package com.ne.jp.shumipro_api.exception

import java.lang.RuntimeException

class ElasticsearchException(msg: String? = null): RuntimeException(msg)