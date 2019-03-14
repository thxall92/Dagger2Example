package com.eunhye.dagger2example.di.component

import com.eunhye.dagger2example.App
import com.eunhye.dagger2example.di.module.ActivityBindingModule
import com.eunhye.dagger2example.di.module.AppModule
import com.eunhye.dagger2example.di.module.RepositoryModule
import com.eunhye.dagger2example.di.module.ViewModelFactoryBindModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
// 한번 의존성이 생성되고 난 후에는 기존 인스턴스를 그대로 사용
@Component (modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    RepositoryModule::class,
    ActivityBindingModule::class,
    ViewModelFactoryBindModule::class
])
// Dagger 코드와 Dagger 안드로이드 코드를 사용하기 위해서는 AndroidSupportInjectionModule 필요

interface AppComponent : AndroidInjector<App> {
//Application과의 연결을 도울 AndroidInjector를 상속받고, 제네릭으로 App클래스를 정의

    @Component.Builder
    // Application과의 연결을 도울 Builder를 정의
    abstract class Build : AndroidInjector.Builder<App>()
}