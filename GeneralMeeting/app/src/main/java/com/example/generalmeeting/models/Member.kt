package com.example.generalmeeting.models

import java.io.Serializable

data class Member(
    var name: String,
    var enable: Boolean,
    var group: Int? = null
): Serializable