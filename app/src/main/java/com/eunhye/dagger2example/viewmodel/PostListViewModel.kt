package com.eunhye.dagger2example.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.eunhye.dagger2example.base.BaseViewModel
import com.eunhye.dagger2example.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel:BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    // MutableLiveData : view will be able to observe in order to updat e the visibility of the ProgressBar

    private lateinit var subscription: Disposable

    init {
        loadPost()
    }

    private fun loadPost(){
        subscription = postApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{ onRetrievePostListStart()}
            .doOnTerminate{ onRetrievePostListFinish()}
            .subscribe(
                { onRetrievePostListSuccess() },
                { onRetrievePostListError() }
            )

    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
    }
    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }
    private fun onRetrievePostListSuccess(){}
    private fun onRetrievePostListError(){}

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}