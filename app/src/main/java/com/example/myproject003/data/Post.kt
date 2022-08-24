package com.example.myproject003.data

data class Post(
    val id :Long,
    val author :String,
    val content :String,
    val published :String,
    val likeByMe :Boolean ,
    val likeCount:Int,
    val repostByMe: Boolean,
    val repostCount :Int
)