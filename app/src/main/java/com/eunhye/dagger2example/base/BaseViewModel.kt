package com.eunhye.dagger2example.base

import androidx.lifecycle.ViewModel
import com.eunhye.dagger2example.di.component.DaggerViewModelInjector
import com.eunhye.dagger2example.di.component.ViewModelInjector
import com.eunhye.dagger2example.di.module.NetworkModule
import com.eunhye.dagger2example.viewmodel.PostListViewModel
import com.eunhye.dagger2example.viewmodel.PostViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector.builder().networkModule(NetworkModule).build()

    init {
        inject()
    }

    private fun inject(){
        when(this){
            is PostListViewModel -> injector.injfect(this)
            is PostViewModel -> injector.injfect(this)
        }
    }
}