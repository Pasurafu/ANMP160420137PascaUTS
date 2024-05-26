package com.example.anmp160420137pascauts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.anmp160420137pascauts.R
import com.example.anmp160420137pascauts.viewmodel.DetailTodoViewModel


class EditTodoFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        txtJudulToDo.text = "Edit Todo"
        buttonSubmit.text = "Save Changes"
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()
        buttonSubmit.setOnClickListener {
            val radio =
                view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            viewModel.update(textJudulToDo.text.toString(), TextInputEdit2.text.toString(),
                radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }




    }
    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            TextInputEdit1.setText(it.title)
            TextInputEdit2.setText(it.notes)

            })
        when (it.priority) {
            1 -> radioLow.isChecked = true
            2 -> radioMedium.isChecked = true
            else -> radioHigh.isChecked = true
        }

    }



    private lateinit var viewModel: DetailTodoViewModel



    companion object {

    }
}