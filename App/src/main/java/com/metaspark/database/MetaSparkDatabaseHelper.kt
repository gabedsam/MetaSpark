package com.metaspark.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MetaSparkDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    companion object {
        private const val DATABASE_NAME = "metaspark.db"
        private const val DATABASE_VERSION = 1
        private const val TAG = "MetaSparkDebug"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE tarefas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT NOT NULL,
                descricao TEXT,
                status TEXT,
                data_criacao INTEGER
            );
        """.trimIndent()
        db.execSQL(createTable)
        Log.d(TAG, "Tabela 'tarefas' criada no banco de dados")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS tarefas")
        onCreate(db)
        Log.d(TAG, "Banco de dados atualizado da versão $oldVersion para $newVersion")
    }
}
