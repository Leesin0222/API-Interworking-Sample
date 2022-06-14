package com.company.sample.remote.api

import com.company.sample.remote.response.UserInfoResponse
import retrofit2.Call
import retrofit2.http.GET


interface GithubApi {
    @GET("users/Leesin0222")
    fun getGithubInfo(): Call<UserInfoResponse>
}