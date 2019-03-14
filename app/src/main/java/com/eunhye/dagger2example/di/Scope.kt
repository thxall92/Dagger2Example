package com.eunhye.dagger2example.di

import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
//@Retention : 어노테이션의 범위, 어떤 시점까지 어노테이션이 영향을 미치는지 결정
annotation class ActivityScoped
