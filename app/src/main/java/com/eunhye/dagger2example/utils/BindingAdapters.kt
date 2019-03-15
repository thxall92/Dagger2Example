package com.eunhye.dagger2example.utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.eunhye.dagger2example.utils.extension.getParentActivity

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?){
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility !=null){
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE })
    }
}