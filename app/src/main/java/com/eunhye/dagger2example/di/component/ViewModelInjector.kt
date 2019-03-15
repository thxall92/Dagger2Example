package com.eunhye.dagger2example.di.component

import com.eunhye.dagger2example.App
import com.eunhye.dagger2example.di.module.NetworkModule
import com.eunhye.dagger2example.viewmodel.PostListViewModel
import com.eunhye.dagger2example.viewmodel.PostViewModel
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {
    fun injfect(postListViewModel: PostListViewModel)
    fun injfect(PostViewModel: PostViewModel)

    @Component.Builder
        interface Builder {
        fun build() : ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}