package com.metaspark.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.metaspark.R
import com.metaspark.models.Tarefa

class TaskAdapter(private val tarefas: List<Tarefa>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.tvTitulo)
        val descricao: TextView = view.findViewById(R.id.tvDescricao)
        val status: TextView = view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val tarefa = tarefas[position]
        holder.titulo.text = tarefa.titulo
        holder.descricao.text = tarefa.descricao
        holder.status.text = tarefa.status
    }

    override fun getItemCount() = tarefas.size
}
