package com.metaspark.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.metaspark.R

class FlowChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_chart)
        val tvFlow = findViewById<TextView>(R.id.tvFlow)
        val flow = """
            [Início]
                |
            [Adicionar Tarefa]
                |
            [Visualizar Lista]
                |
            [Ver Gráfico]
                |
            [Ver Árvore]
                |
            [Fim]
        """.trimIndent()
        tvFlow.text = flow
    }
}
