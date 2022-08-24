package com.ilyasmacit.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.ilyasmacit.todoapp.databinding.ActivityMainBinding
import com.ilyasmacit.todoapp.fragment.MainFragment
import com.ilyasmacit.todoapp.fragment.MainFragmentDirections

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun addTodo(view: View){
        var action = MainFragmentDirections.actionMainFragmentToSheetFragment()
        Navigation.findNavController(this , R.id.fragmentContainerView2).navigate(action)

    }
}