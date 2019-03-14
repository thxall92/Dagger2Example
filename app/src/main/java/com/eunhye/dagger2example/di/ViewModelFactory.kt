package com.eunhye.dagger2example.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

/**
 * AAC 의 ViewModelProvider.Factory 를 상속하는 클래스를 제작.
 * 생성자 Provider은 해당 ViewModel 에 대한 생성자를 제공할 수 있다
 */

class ViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        /**
         * creators key로 찾는데 해당 값이 없으면, modelClass가 접근 가능한 요소를 찾아서 값을 찾아낸다
         * 그래도 값이 없으면 IllegalArgumentException 예외를 발생시킨다
         */

        val found = creators.entries.find { modelClass.isAssignableFrom(it.key)}
        val creator = found?.value
            ?: throw IllegalArgumentException("unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        }catch (e:Exception){
            throw RuntimeException(e)
        }
    }
}