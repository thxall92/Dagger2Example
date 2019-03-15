package com.eunhye.dagger2example.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
//@Entity that can be saved in the database
data class Post(
    val userId: Int,
    @field:PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)