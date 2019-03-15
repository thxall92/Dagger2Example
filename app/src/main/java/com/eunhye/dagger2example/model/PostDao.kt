package com.eunhye.dagger2example.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
//@Dao를 이용해 공통으로 사용하는 Insert, Update, Delete메서드를 정의하면 다른 곳에서 상속받아 사용할 수 있다
interface PostDao {
    @get:Query("SELECT * FROM post")
    val all: List<Post>

    @Insert
    fun insertAll(vararg users: Post)
}