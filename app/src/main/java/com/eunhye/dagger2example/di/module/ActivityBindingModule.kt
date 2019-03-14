package com.eunhye.dagger2example.di.module

import com.eunhye.dagger2example.di.ActivityScoped
import com.eunhye.dagger2example.di.ViewModelKey
import com.eunhye.dagger2example.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * ContributesAndroidInjector 어노테이션을 달고 반환타입을 통해
 * 해당 Activity의 Subcomponent를 생성한다.
 * modules에 Subcomponent와 연결할 Module을 정의한다.
 * 이 Module들이 실제 의존성 객체를 생성한다.
 */

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules=[ViewModelBindModule::class])
    internal abstract fun mainActivity(): MainActivity
}

