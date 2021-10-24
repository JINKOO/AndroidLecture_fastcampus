package com.jkko.todolist.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jkko.todolist.VO.Todo

class MainViewModel : ViewModel() {

    // 변경 가능, 관찰 가능한
    val todoLiveData = MutableLiveData<List<Todo>>()

    // 바깥에서 수정할 수 없도록 변경
    private val data = arrayListOf<Todo>()

    // 추가 버튼을 클릭했을 때
    fun addTodo(todo: Todo) {
        data.add(todo)
        todoLiveData.value = data
    }

    //
    fun deleteTodo(todo: Todo) {
        data.remove(todo)
        todoLiveData.value = data
    }

    // 클릭한 item값의 isDone을 반대값으로 설정한다.
    fun toggleTodo(todo: Todo) {
        todo.isDone = !todo.isDone
        todoLiveData.value = data
    }
}