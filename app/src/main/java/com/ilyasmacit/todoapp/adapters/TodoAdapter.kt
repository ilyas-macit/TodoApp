package com.ilyasmacit.todoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilyasmacit.todoapp.databinding.TodoViewBinding
import com.ilyasmacit.todoapp.models.Todo

class TodoAdapter(val todoList: List<Todo>, val sectionAdapter:  SectionAdapter) : RecyclerView.Adapter<TodoAdapter.TodoHolder>() {
    class TodoHolder(val binding: TodoViewBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val binding = TodoViewBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return TodoHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.binding.todoTitle.text = todoList[position].name
    }

    override fun getItemCount(): Int = todoList.size
}