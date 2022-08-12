package com.example.studentslist.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.launch
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
                lifecycleScope.launch {
                    try {
                        addStudentViewModel.save(
                            et_addNewStudent_firstName.text.toString(),
                            et_addNewStudent_lastName.text.toString(),
                            et_addNewStudent_course.text.toString(),
                            et_addNewStudent_score.text.toString()
                        ).also {
                            val intent = Intent(applicationContext, HomeActivity::class.java).apply {
                                putExtra("student",it)
                                setResult(Activity.RESULT_OK)
                            }
                            startActivity(intent)
                            finish()
                        }
                    }catch (e : Exception){
                        Toast.makeText(this@AddStudentActivity,e.toString(),Toast.LENGTH_SHORT).show()
                    }


                }


            }
        })


    }
}