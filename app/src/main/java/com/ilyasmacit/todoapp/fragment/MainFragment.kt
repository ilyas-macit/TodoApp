package com.ilyasmacit.todoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ilyasmacit.todoapp.R
import com.ilyasmacit.todoapp.adapters.SectionAdapter
import com.ilyasmacit.todoapp.databinding.FragmentMainBinding
import com.ilyasmacit.todoapp.databinding.FragmentSheetBinding
import com.ilyasmacit.todoapp.models.Section
import com.ilyasmacit.todoapp.models.Todo
import com.ilyasmacit.todoapp.roomdb.TodoDao
import com.ilyasmacit.todoapp.roomdb.TodoDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var db : TodoDatabase
    private lateinit var todoDao: TodoDao
    private lateinit var section : Section
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(requireContext() , TodoDatabase::class.java , "Todos").build()
        todoDao = db.todoDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compositeDisposable.add(
            todoDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )

    }

    fun handleResponse(todoList : List<Todo>){
        val sectionList = ArrayList<Section>()
        sectionList.add(Section("new" , splitList("new",todoList) ))
        sectionList.add(Section("failed" , splitList("failed",todoList) ))
        sectionList.add(Section("completed" , splitList("completed",todoList) ))

        println(sectionList[2])


        val adapter = SectionAdapter(sectionList , requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

    }

    fun splitList( status : String,todoList : List<Todo>) : List<Todo>{
        val tempList  = ArrayList<Todo>()
        for (todo in todoList){
            if (todo.state == status){
                tempList.add(todo)
            }
        }

        return tempList
    }



}