package com.metaspark.activities

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.metaspark.R
import com.metaspark.database.MetaSparkDatabaseHelper

class AddTaskActivity : AppCompatActivity() {
    private val TAG = "MetaSparkDebug"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etDescricao = findViewById<EditText>(R.id.etDescricao)
        val spinnerStatus = findViewById<Spinner>(R.id.spinnerStatus)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        val statusList = listOf("Pendente", "Em andamento", "Concluída")
        spinnerStatus.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, statusList)

        btnSalvar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val descricao = etDescricao.text.toString()
            val status = spinnerStatus.selectedItem.toString()
            val dbHelper = MetaSparkDatabaseHelper(this)
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put("titulo", titulo)
                put("descricao", descricao)
                put("status", status)
                put("data_criacao", System.currentTimeMillis())
            }

            val newRowId = db.insert("tarefas", null, values)
            Log.d(TAG, "Nova tarefa inserida: $newRowId")
            finish()
        }
    }
}
