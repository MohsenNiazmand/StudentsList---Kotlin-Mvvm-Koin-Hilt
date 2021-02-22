package com.example.studentslist.common

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class StudentActivity:AppCompatActivity(),StudentView{


}


interface StudentView{


}


abstract class StudentViewModel:ViewModel(){
    val compositeDisposable = CompositeDisposable()


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}