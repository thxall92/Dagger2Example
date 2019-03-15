package com.eunhye.dagger2example.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.eunhye.dagger2example.R
import com.eunhye.dagger2example.databinding.ActivityPostListBinding
import com.eunhye.dagger2example.viewmodel.PostListViewModel

class PostListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: PostListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}