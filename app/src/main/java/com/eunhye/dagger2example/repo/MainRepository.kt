package com.eunhye.dagger2example.repo

import com.eunhye.dagger2example.data.LocalDataSource
import javax.inject.Inject

/**
 * Repository
 *
 * Observable과 Supplier를 상속
 * 내부에 데이터를 가지고 있음
 * 데이터가 변경되면(Observable) Stream의 시작함(Supplier)
 */

class MainRepository @Inject constructor(private val localDataSource: LocalDataSource){
    fun getName(name: String): String = localDataSource.getName(name)
}