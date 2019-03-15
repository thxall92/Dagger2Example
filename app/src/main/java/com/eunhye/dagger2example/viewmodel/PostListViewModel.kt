package com.eunhye.dagger2example.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.eunhye.dagger2example.R
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
    // MutableLiveData : view will be able to observe in order to updat e the visibility of the ProgressBar

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListner = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    private fun loadPosts(){
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
        errorMessage.value = null
    }
    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }
    private fun onRetrievePostListSuccess(){}
    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}