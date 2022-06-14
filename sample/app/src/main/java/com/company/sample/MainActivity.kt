package com.company.sample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.company.sample.databinding.ActivityMainBinding
import com.company.sample.remote.RetrofitBuilder
import com.company.sample.remote.api.GithubApi
import com.company.sample.remote.response.UserInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RetrofitBuilder.githubApi.getGithubInfo().enqueue(object : Callback<UserInfoResponse> {
            override fun onResponse(
                call: Call<UserInfoResponse>,
                response: Response<UserInfoResponse>,
            ) {
                val userInfo = response.body()

                binding.followerNum.text = userInfo!!.followers.toString()
                binding.followingNum.text = userInfo.following.toString()
                binding.description.text = userInfo?.bio

                if (userInfo.bio == null) {
                    binding.description.text = "설명이 없습니다."
                }

                if (userInfo.name == null) {
                    binding.name.text = userInfo.login
                } else {
                    binding.name.text = userInfo.name
                }

                getImageURL(userInfo.avatar_url)
            }

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "정보를 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }

            fun getImageURL(imageURL: String) {

                Glide.with(applicationContext)
                    .load(imageURL)
                    .into(binding.ivProfile)
            }
        })


    }
}