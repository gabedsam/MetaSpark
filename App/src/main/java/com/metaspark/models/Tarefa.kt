package com.metaspark.models

data class Tarefa(
    val id: Int,
    val titulo: String,
    val descricao: String,
    val status: String,
    val dataCriacao: Long
)
