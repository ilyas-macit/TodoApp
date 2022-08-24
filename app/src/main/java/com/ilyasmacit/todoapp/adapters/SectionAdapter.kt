package com.ilyasmacit.todoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilyasmacit.todoapp.databinding.SectionViewBinding
import com.ilyasmacit.todoapp.models.Section

class SectionAdapter(var section : List<Section>  , val context: Context): RecyclerView.Adapter<SectionAdapter.SectionHolder>(){
    class SectionHolder(var binding : SectionViewBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionHolder {
        var binding = SectionViewBinding.inflate(LayoutInflater.from(parent.context) , parent ,false)
        return SectionHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionHolder, position: Int) {
        holder.binding.sectionTitle.text = section[position].title
        //todoAdapter
        val adapter = TodoAdapter(section[position].todoList , this)
        holder.binding.todoRecyclerView.layoutManager = LinearLayoutManager(context)
        holder.binding.todoRecyclerView.adapter = adapter

    }

    override fun getItemCount(): Int = section.size
}