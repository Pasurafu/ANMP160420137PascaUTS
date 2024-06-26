package com.example.anmp160420137pascauts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp160420137pascauts.R
import com.example.anmp160420137pascauts.databinding.FragmentTodoListBinding
import com.example.anmp160420137pascauts.viewmodel.ListTodoViewModel


class TodoListFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private lateinit var viewModel: ListTodoViewModel
    private val todoListAdapter  = TodoListAdapter(arrayListOf())
    private lateinit var binding:FragmentTodoListBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListTodoViewModel::class.java)
        viewModel.refresh()
        binding.recViewToDo.layoutManager = LinearLayoutManager(context)
        binding.recViewToDo.adapter = todoListAdapter
        binding.btnFab.setOnClickListener {
            val action = TodoListFragmentDirections.todoListToTodoFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoListBinding.inflate(inflater,container,false)
        return  binding.root


    }
    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            todoListAdapter.updateTodoList(it)
            if(it.isEmpty()) {
                binding.recViewToDo?.visibility = View.GONE
                binding.txtError.setText("Your todo still empty.")
            } else {
                binding.recViewToDo?.visibility = View.VISIBLE
            }
            viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
                if(it == false) {
                    binding.progressBar?.visibility = View.GONE
                } else {
                    binding.progressBar?.visibility = View.VISIBLE
                }
            })
            viewModel.todoLoadErrorLD.observe(viewLifecycleOwner, Observer {
                if(it == false) {
                    binding.txtError?.visibility = View.GONE
                } else {
                    binding.txtError?.visibility = View.VISIBLE
                }
            })


        })
    }

    companion object {

    }
}