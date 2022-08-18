package com.example.studentslist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentslist.R
import com.example.studentslist.model.common.implementSpringAnimationTrait
import com.example.studentslist.model.data.Student


class StudentAdapter() : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {


    var students = ArrayList<Student>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullNameTv = itemView.findViewById<TextView>(R.id.tv_student_fullName)
        val courseTitleTv = itemView.findViewById<TextView>(R.id.tv_student_course)
        val scoreTv = itemView.findViewById<TextView>(R.id.tv_student_score)
        val firstCharacterTv = itemView.findViewById<TextView>(R.id.tv_student_firstCharacter)


        fun bindStudent(student: Student) {
            fullNameTv.text = student.first_name + " " + student.last_name
            courseTitleTv.text = student.course
            scoreTv.text = student.score.toString()
            firstCharacterTv.text = student.first_name.substring(0, 1)
//            itemView.implementSpringAnimationTrait()
            itemView.setOnClickListener {

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindStudent(students[position])
    }

    override fun getItemCount(): Int = students.size


    fun addStudent(student: Student) {
        this.students.add(0, student)
        notifyItemInserted(0)
    }
}