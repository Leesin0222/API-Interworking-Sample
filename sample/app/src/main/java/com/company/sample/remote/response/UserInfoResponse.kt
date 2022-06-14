package com.company.sample.remote.response

data class UserInfoResponse(
    val avatar_url: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val login: String,
    val name: String
)