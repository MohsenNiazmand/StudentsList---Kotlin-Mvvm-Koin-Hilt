package com.example.studentslist.model.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentslist.R
import com.example.studentslist.model.common.extension.showGenericAlertDialog
import com.example.studentslist.model.data.ErrorResponse
import com.example.studentslist.viewmodel.AddStudentActivityState
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.IllegalStateException

abstract class StudentActivity:AppCompatActivity(),StudentView{
    override val rootView: CoordinatorLayout?
        get() {
            val viewGroup = window.decorView.findViewById(android.R.id.content) as ViewGroup
            if (viewGroup !is CoordinatorLayout){

                viewGroup.children.forEach {
                    if (it is CoordinatorLayout)
                        return it
                }
                throw IllegalStateException("RootView must be instance of coordinator layout")
            }else{
                return viewGroup
            }
        }
    override val viewContext: Context?
        get() = this



}


interface StudentView{
    val rootView: CoordinatorLayout?
    val viewContext: Context?
    fun setProgressIndicator(mustShow :Boolean){
        rootView?.let{
            viewContext?.let { context ->
                var loadingView=it.findViewById<View>(R.id.progressBar)
                if (loadingView==null && mustShow){
                    loadingView= LayoutInflater.from(context).inflate(R.layout.view_loading,it,false)
                    it.addView(loadingView)
                }
                loadingView?.visibility = if (mustShow) View.VISIBLE else View.GONE
            }
        }

    }


}



