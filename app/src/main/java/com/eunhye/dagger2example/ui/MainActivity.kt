package com.eunhye.dagger2example.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.eunhye.dagger2example.R
import com.eunhye.dagger2example.ui.post.PostListActivity
import com.eunhye.dagger2example.viewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    // DaggerAppCompatActivity
    // onCreate() 함수 안에서 멤버들을 주입시키고, fragment도 주입시키는데 사용될 수 있다.

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, factory)[MainViewModel::class.java]
        // view model 초기화
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeUI()
        mainViewModel.getUser("홍길동")

        button.setOnClickListener{
            startActivity(Intent(this, PostListActivity::class.java))
        }
    }

    @SuppressLint("CheckResult")
    private fun subscribeUI(){
        mainViewModel.compositeDisposable.add(mainViewModel.userOB.
            observeOn(AndroidSchedulers.mainThread())
            .subscribe{ user ->
                when(user){
                    is MainViewModel.User.Username -> textview.text = user.name
                }
            })
    }
}
