package com.eunhye.dagger2example

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.eunhye.dagger2example.di.component.DaggerAppComponent

class App: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        // Component 연결
        return DaggerAppComponent.builder().create(this)
    }
}