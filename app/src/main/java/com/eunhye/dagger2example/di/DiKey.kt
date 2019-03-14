package com.eunhye.dagger2example.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
// 어노테이션이 적용할 위치를 결정
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
// @Mapkey를 이용해 ViewModelKey가 Multi Binding에서 사용되는 Key