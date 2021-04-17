package com.ne.jp.shumipro_api.request

import javax.validation.constraints.NotNull

data class TaskCommentRequest(

    @field:NotNull(message="{e.001}")
    var comment: String?

) {

}