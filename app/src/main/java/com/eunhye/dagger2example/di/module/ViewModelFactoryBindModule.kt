package com.eunhye.dagger2example.di.module

import androidx.lifecycle.ViewModelProvider
import com.eunhye.dagger2example.di.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 *  생성한 DaggerViewModelFactory 클래스를 @Binds 를 통해 @Module 에 설정
 */

@Module
internal abstract class ViewModelFactoryBindModule{

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory
}
