package com.ubaya.studentapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.studentapp.databinding.StudentListItemBinding
import com.ubaya.studentapp.model.Student
import java.lang.Exception

class StudentListAdapter (val studentList:ArrayList<Student>): RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonClickListener {
    class StudentViewHolder(var binding: StudentListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception ->
////            listener yg ketrigger waktu picasso error (muncul di logcat)
//            exception.printStackTrace()
//        }
//        picasso.build().load(studentList[position].photoUrl).into(holder.binding.imageView, object:Callback {
//            override fun onSuccess() {
//                holder.binding.progressBar.visibility = View.INVISIBLE
//                holder.binding.imageView.visibility = View.VISIBLE
//            }
//
//            override fun onError(e: Exception?) {
//                Log.e("picasso_error", e.toString())
//            }
//        })
//
//        holder.binding.txtID.text = studentList[position].id
//        holder.binding.txtName.text = studentList[position].name
//        holder.binding.btnDetail.setOnClickListener {
//            val action = StudentListFragmentDirections.actionStudentDetail(studentList[position].id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }

        holder.binding.student = studentList[position]
        holder.binding.listener = this

    }

    fun updateStudentList(newStudentList:ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}