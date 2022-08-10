package com.example.studentslist.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.studentslist.R
import com.example.studentslist.viewmodel.AddStudentViewModel
import com.example.studentslist.model.common.StudentActivity
import com.example.studentslist.model.data.Student
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_student.*
import timber.log.Timber

@AndroidEntryPoint
class AddStudentActivity : StudentActivity() {

    val compositeDisposable = CompositeDisposable()
    private val addStudentViewModel: AddStudentViewModel by viewModels()
//    @Inject lateinit var addStudentViewModel:AddStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        fab_addNewStudent_save.setOnClickListener(View.OnClickListener {
            if (et_addNewStudent_firstName.length() > 0
                    && et_addNewStudent_lastName.length() > 0
                    && et_addNewStudent_course.length() > 0
                    && et_addNewStudent_score.length() > 0
            ) {
                addStudentViewModel.save(
                        et_addNewStudent_firstName.text.toString(),
                        et_addNewStudent_lastName.text.toString(),
                        et_addNewStudent_course.text.toString(),
                        et_addNewStudent_score.text.toString()
                )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : SingleObserver<Student> {
                            override fun onSubscribe(d: Disposable) {
                                compositeDisposable.add(d)
                            }

                            override fun onSuccess(t: Student) {
                                val intent = Intent(applicationContext, HomeActivity::class.java).apply {
                                    putExtra("student",t)
                                    setResult(Activity.RESULT_OK)
                                }
                                startActivity(intent)

                                finish()
                            }

                            override fun onError(e: Throwable) {
                                Timber.e(e)
                            }

                        })
            }
        })

    }
}