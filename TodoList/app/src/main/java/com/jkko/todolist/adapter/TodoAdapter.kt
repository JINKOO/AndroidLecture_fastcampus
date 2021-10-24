package com.jkko.todolist.adapter

import android.graphics.Paint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jkko.todolist.R
import com.jkko.todolist.VO.Todo
import com.jkko.todolist.databinding.ItemTodoBinding

class TodoAdapter(
    private var myDataSet: List<Todo>,
    val onClickDeleteIcon: (todo: Todo) -> Unit,
    val onClickItem: (todo: Todo) -> Unit
) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)

        return TodoViewHolder(ItemTodoBinding.bind(view))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        val todo = myDataSet[position]
        holder.binding.todoText.text = todo.text

        if (todo.isDone) {
//            holder.binding.todoText.paintFlags = holder.binding.todoText.paintFlags or
//                    Paint.STRIKE_THRU_TEXT_FLAG
            holder.binding.todoText.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                setTypeface(null, Typeface.ITALIC)
            }
        } else {
            holder.binding.todoText.apply {
                paintFlags = 0
                setTypeface(null, Typeface.NORMAL)
            }
        }

        //
        holder.binding.deleteImageView.setOnClickListener {
            // Main Activity에 알려줘야 한다.
            onClickDeleteIcon.invoke(todo)
        }

        //
        holder.binding.root.setOnClickListener {
            onClickItem.invoke(todo)
        }
    }

    override fun getItemCount(): Int = myDataSet.size

    // 추가 코드
    fun setData(newData: List<Todo>) {
        myDataSet = newData
        notifyDataSetChanged()
    }

    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}