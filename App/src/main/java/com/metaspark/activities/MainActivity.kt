package com.metaspark.activities

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.metaspark.R
import com.metaspark.adapters.TaskAdapter
import com.metaspark.database.MetaSparkDatabaseHelper
import com.metaspark.models.Tarefa
import com.metaspark.utils.NotificationUtil
import com.metaspark.utils.ExportUtil

class MainActivity : AppCompatActivity() {
    private val TAG = "MetaSparkDebug"
    private lateinit var dbHelper: MetaSparkDatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var chart: PieChart
    private lateinit var btnAdd: Button
    private lateinit var btnTree: Button
    private lateinit var btnFlow: Button
    private lateinit var btnExport: Button
    private lateinit var btnNotify: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "MainActivity criada")

        dbHelper = MetaSparkDatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        chart = findViewById(R.id.pieChart)
        btnAdd = findViewById(R.id.btnAddTask)
        btnTree = findViewById(R.id.btnTree)
        btnFlow = findViewById(R.id.btnFlow)
        btnExport = findViewById(R.id.btnExport)
        btnNotify = findViewById(R.id.btnNotify)

        recyclerView.layoutManager = LinearLayoutManager(this)
        loadTasks()

        btnAdd.setOnClickListener { startActivity(Intent(this, AddTaskActivity::class.java)) }
        btnTree.setOnClickListener { startActivity(Intent(this, TreeActivity::class.java)) }
        btnFlow.setOnClickListener { startActivity(Intent(this, FlowChartActivity::class.java)) }
        btnExport.setOnClickListener { ExportUtil.exportTasksToCSV(this, getAllTasks()) }
        btnNotify.setOnClickListener { NotificationUtil.showNotification(this, "MetaSpark", "Você tem tarefas pendentes!") }
    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    private fun loadTasks() {
        val tarefas = getAllTasks()
        recyclerView.adapter = TaskAdapter(tarefas)
        Log.d(TAG, "Tarefas carregadas: ${tarefas.size}")

        // Gráfico de tarefas por status
        val statusCount = tarefas.groupingBy { it.status }.eachCount()
        val entries = statusCount.map { PieEntry(it.value.toFloat(), it.key) }
        val dataSet = PieDataSet(entries, "Status")
        chart.data = PieData(dataSet)
        chart.invalidate()
        Log.d(TAG, "Gráfico atualizado")
    }

    private fun getAllTasks(): List<Tarefa> {
        val tarefas = mutableListOf<Tarefa>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM tarefas", null)
        while (cursor.moveToNext()) {
            val tarefa = Tarefa(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao")),
                status = cursor.getString(cursor.getColumnIndexOrThrow("status")),
                dataCriacao = cursor.getLong(cursor.getColumnIndexOrThrow("data_criacao"))
            )
            tarefas.add(tarefa)
        }
        cursor.close()
        return tarefas
    }
}
