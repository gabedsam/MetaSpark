package com.metaspark.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.metaspark.R

class TreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree)
        val tvTree = findViewById<TextView>(R.id.tvTree)
        val tree = """
            MetaSpark
            ├── Tarefas
            │   ├── Pendente
            │   ├── Em andamento
            │   └── Concluída
            └── Relatórios
                └── Gráficos
        """.trimIndent()
        tvTree.text = tree
    }
}
