package com.eunhye.dagger2example.viewmodel

import androidx.lifecycle.ViewModel
import com.eunhye.dagger2example.repo.MainRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel(){

    sealed class User {
        data class Username(val name: String) : User()
    }

    /**
     * Subject : Observable<R>를 상속받고 Observer`를 구현하는 추상 클래스
     * PublishSubject : onNext 요청이 들어오면 state를 통해 결과 여부(onError이나 onCompleted)를 확인해
     *                  리스트에 있는 항목을 먼저 수행할지(emitFirst), 늦게 수행할지(emitNext) 판단해
     * */

    private val userSubject: PublishSubject<User> = PublishSubject.create()
    val userOB: Observable<User>
    val compositeDisposable = CompositeDisposable()

    init {
        userOB = userSubject
    }

    fun getUser(name: String) {
        userSubject.onNext(User.Username(mainRepository.getName(name)))
    }

    /**
     *  RxJava의 경우 LiveData와는 다르게 lifecycle을 알지 못하기 때문에
     *  ViewModel의 onCleared()가 호출되면(Activity 혹은 Fragment의 onDestroy 시점)
     *  명시적으로 dispose해주어야 메모리릭을 방지할 수 있습니다.
     * */
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}