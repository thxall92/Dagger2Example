package com.eunhye.dagger2example.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.eunhye.dagger2example.R
import com.eunhye.dagger2example.base.BaseViewModel
import com.eunhye.dagger2example.model.Post
import com.eunhye.dagger2example.model.PostDao
import com.eunhye.dagger2example.network.PostApi
import com.eunhye.dagger2example.ui.post.PostListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao):BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
    val postListAdapter: PostListAdapter = PostListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    // MutableLiveData : view will be able to observe in order to updat e the visibility of the ProgressBar

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListner = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    private fun loadPosts(){
        subscription = Observable.fromCallable{ postDao.all }
            .concatMap { dbPostList ->
                if(dbPostList.isEmpty())
                    postApi.getPosts().concatMap {
                        apiPostList -> postDao.insertAll(*apiPostList.toTypedArray())
                        Observable.just(apiPostList)
                    }
                else
                    Observable.just(dbPostList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
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
    private fun onRetrievePostListSuccess(postList:List<Post>){
        postListAdapter.updatePostList(postList)
    }
    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}