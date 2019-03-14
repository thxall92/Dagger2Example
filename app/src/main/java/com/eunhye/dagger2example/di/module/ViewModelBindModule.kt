package com.eunhye.dagger2example.di.module

import androidx.lifecycle.ViewModel
import com.eunhye.dagger2example.di.ActivityScoped
import com.eunhye.dagger2example.di.ViewModelKey
import com.eunhye.dagger2example.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @IntoMap과 @Binds로 ViewModel 및 ViewModelFactory의 객체 생성을 관리한다
 * @IntoMap을 이용한 MultiBinding은 하나의 객체를 주입하는게 아니라 객체를 담고 있는 컬렉션 자체를 주입한
 */

@Module
internal abstract class ViewModelBindModule{

    @ActivityScoped
    @Binds
    // @Provides 와 같이 객체를 주입해주는 어노테이션인데,
    // 인터페이스(또는 추상클래스)를 사용하는 경우 @Binds를 사용
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}