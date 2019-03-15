package com.eunhye.dagger2example.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
//Dao class to allow us to insert and retrieve Posts from the database
interface PostDao {
    @get:Query("SELECT * FROM post")
    val all: List<Post>

    @Insert
    fun insertAll(vararg users: Post)
}