package com.metaspark.utils

import android.content.Context
import android.widget.Toast
import com.metaspark.models.Tarefa
import java.io.File
import java.io.FileWriter

object ExportUtil {
    fun exportTasksToCSV(context: Context, tarefas: List<Tarefa>) {
        try {
            val file = File(context.getExternalFilesDir(null), "tarefas.csv")
            val writer = FileWriter(file)
            writer.append("ID,Título,Descrição,Status,Data\n")
            for (t in tarefas) {
                writer.append("${t.id},${t.titulo},${t.descricao},${t.status},${t.dataCriacao}\n")
            }
            writer.flush()
            writer.close()
            Toast.makeText(context, "Exportado para ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Erro ao exportar: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
