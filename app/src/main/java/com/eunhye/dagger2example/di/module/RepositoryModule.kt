package com.eunhye.dagger2example.di.module

import com.eunhye.dagger2example.data.LocalDataSource
import com.eunhye.dagger2example.repo.MainRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule{

    @Provides
    fun getLocalDataSource() = LocalDataSource()

    @Provides
    fun getMainRepo(localDataSource: LocalDataSource): MainRepository = MainRepository(localDataSource)
}