package com.ilyasmacit.todoapp.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.room.Room
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ilyasmacit.todoapp.MainActivity
import com.ilyasmacit.todoapp.R
import com.ilyasmacit.todoapp.databinding.FragmentSheetBinding
import com.ilyasmacit.todoapp.models.Todo
import com.ilyasmacit.todoapp.roomdb.TodoDao
import com.ilyasmacit.todoapp.roomdb.TodoDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class SheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSheetBinding
    private lateinit var todo : Todo
    private lateinit var db : TodoDatabase
    private lateinit var todoDao: TodoDao
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(requireContext() , TodoDatabase::class.java , "Todos").build()
        todoDao = db.todoDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSheetBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveBtn.setOnClickListener {
            save()
        }

    }


    fun save(){
        //create todo
        todo = Todo(binding.todoName.text.toString())
        //save to db
        compositeDisposable.add(
            todoDao.insert(todo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }


    fun handleResponse(){
        var action = SheetFragmentDirections.actionSheetFragmentToMainFragment()
        Navigation.findNavController(requireActivity() , R.id.fragmentContainerView2).navigate(action)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        //dialog

    }

}