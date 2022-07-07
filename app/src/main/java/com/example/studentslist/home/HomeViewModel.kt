package com.example.studentslist.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.studentslist.common.StudentViewModel
import com.example.studentslist.data.Student
import com.example.studentslist.data.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(val homeRepository: HomeRepository) : StudentViewModel() {
    val error = MutableLiveData<String>()
    init {
        progressBarLiveData.value=true
        homeRepository.refreshStudents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally{progressBarLiveData.postValue(false)}
                .subscribe(object : CompletableObserver{
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onComplete() {
                        Timber.i("Success")
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e)
                        error.postValue("خطای نامشخص")
                    }

                })
    }

    fun getStudent() : LiveData<List<Student>> = homeRepository.getStudents()


}