package com.ubaya.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.squareup.picasso.Picasso
import com.ubaya.studentapp.R
import com.ubaya.studentapp.databinding.FragmentStudentDetailBinding
import com.ubaya.studentapp.model.Student
import com.ubaya.studentapp.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonClickListener {
    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initialize dulu, biar ga null
        binding.detail = Student("", "", "", "", "https://randomuser.me/api/portraits/men/51.jpg")

        if (arguments != null) {
            val id =StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(id)
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
//            Picasso.get().load(viewModel.studentLD.value?.photoUrl).into(binding.imageView)
            binding.detail = it
            binding.listener = this
//            binding.txtID.setText(viewModel.studentLD.value?.id)
//            binding.txtName.setText(viewModel.studentLD.value?.name)
//            binding.txtBOD.setText(viewModel.studentLD.value?.dob)
//            binding.txtPhone.setText(viewModel.studentLD.value?.phone)

//            val student = it
//            binding.btnUpdate?.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "Five Seconds")
//                        MainActivity.showNotification(student.name.toString(), "A new notification created", R.drawable.material_symbols__person_add)
//                    }
//            }
        })
    }

    override fun onButtonClick(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "Five Seconds")
                MainActivity.showNotification(binding.detail?.name.toString(), "A new notification created", R.drawable.material_symbols__person_add)
            }
    }
}