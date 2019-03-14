package com.eunhye.dagger2example.di.module

import android.content.Context
import com.eunhye.dagger2example.App
import dagger.Module
import dagger.Provides

@Module
class AppModule{
    // Context 타입의 의존성 객체를 생성

    @Provides
    //Provides 어노테이션으로 의존성 객체를 생성할 메소드를 정의
    fun provideContext(app: App): Context {
        return app.applicationContext
    }
}