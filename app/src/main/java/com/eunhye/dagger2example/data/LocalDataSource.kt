package com.eunhye.dagger2example.data

interface LocalData {
    fun getName(name: String): String
}

class LocalDataSource : LocalData {
    override fun getName(name: String): String {
        return name + "입니다."
    }
}