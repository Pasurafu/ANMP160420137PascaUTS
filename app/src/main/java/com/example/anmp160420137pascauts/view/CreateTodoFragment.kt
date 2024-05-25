package com.example.anmp160420137pascauts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.anmp160420137pascauts.R
import com.example.anmp160420137pascauts.databinding.FragmentCreateTodoBinding
import com.example.anmp160420137pascauts.model.Todo

class CreateTodoFragment : Fragment() {
    private lateinit var binding: FragmentCreateTodoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding.buttonSubmit.setOnClickListener {      var todo = Todo(		binding.TextInputEdit1.text.toString(),
            binding.TextInputEdit2.text.toString()
        )
            val list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
        binding = FragmentCreateTodoBinding.inflate(inflater,container,false)
        return binding.root




    }

    companion object {

    }
}