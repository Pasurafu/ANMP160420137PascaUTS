package com.example.anmp160420137pascauts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.anmp160420137pascauts.model.Todo
import com.example.anmp160420137pascauts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val db = buildDb(getApplication())
    fun addTodo(list: List<Todo>) {
        launch {
            val db = TodoDatabase.buildDatabase(
                getApplication()
            )
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
    val todoLD = MutableLiveData<Todo>()
    fun fetch(uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            todoLD.value =  db.todoDao().selectTodo(uuid)
        }
    }
    fun update(title:String, notes:String, priority:Int, uuid:Int) {
        launch {
            val db = buildDB(getApplication())
            db.todoDao().update(title, notes, priority, uuid)
        }
    }

}

