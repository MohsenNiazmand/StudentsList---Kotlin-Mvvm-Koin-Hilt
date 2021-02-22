package com.example.studentslist.home

import androidx.lifecycle.MutableLiveData
import com.example.studentslist.common.StudentViewModel
import com.example.studentslist.data.Student
import com.example.studentslist.data.repositories.HomeRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class HomeViewModel(val homeRepository: HomeRepository) : StudentViewModel() {
    val studentsLiveData = MutableLiveData<List<Student>>()
    val error = MutableLiveData<String>()
    init {
        homeRepository.getStudents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object  : SingleObserver<List<Student>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Student>) {
                    studentsLiveData.value=t
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    error.postValue("خطای نامشخص")
                }


            })
    }


}