package com.jkko.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkko.todolist.VO.Todo
import com.jkko.todolist.adapter.TodoAdapter
import com.jkko.todolist.databinding.ActivityMainBinding
import com.jkko.todolist.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

//    private val data = arrayListOf<Todo>()

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        this.viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //
//        binding.rvTodo.layoutManager = LinearLayoutManager(this)
//        binding.rvTodo.adapter = TodoAdapter(data,
//            onClickDeleteIcon = {
//                deleteTodo(it)
//            }
//        )

        binding.rvTodo.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoAdapter(
                myDataSet = emptyList(),
                onClickDeleteIcon = {
//                    deleteTodo(it)
                    viewModel.deleteTodo(it)
//                    binding.rvTodo.adapter?.notifyDataSetChanged()  // LiveData적용하면, 사용하지 않음.
                },
                onClickItem = {
//                    toggleTodo(it)
                    viewModel.toggleTodo(it)
//                    binding.rvTodo.adapter?.notifyDataSetChanged()
                }
            )
        }

        //
        binding.addButton.setOnClickListener {
            val todo = Todo(binding.editTextTodo.text.toString())
//            addTodo(todo)
            viewModel.addTodo(todo)
//            binding.rvTodo.adapter?.notifyDataSetChanged()
        }

        // 관찰 및 UI업데이트
        // viewModel의 todoLiveData의 저장된 data가 변경될 때 마다, 호출된다.
        // notifyDataSetChanged를 LiveData를 사용한다.
        viewModel.todoLiveData.observe(this, Observer {
            (binding.rvTodo.adapter as TodoAdapter).setData(it) // casting
        })
    }


//    // 추가 버튼을 클릭했을 때
//    private fun addTodo() {
//        val todo = Todo(binding.editTextTodo.text.toString())
//        data.add(todo)
//        binding.rvTodo.adapter?.notifyDataSetChanged()
//    }
//
//    //
//    private fun deleteTodo(todo: Todo) {
//        data.remove(todo)
//        binding.rvTodo.adapter?.notifyDataSetChanged()
//    }
//
//    // 클릭한 item값의 isDone을 반대값으
//    private fun toggleTodo(todo: Todo) {
//        todo.isDone = !todo.isDone
//        binding.rvTodo.adapter?.notifyDataSetChanged()
//    }
}
