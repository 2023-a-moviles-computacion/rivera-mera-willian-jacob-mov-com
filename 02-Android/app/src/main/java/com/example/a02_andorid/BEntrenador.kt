package com.example.a02_andorid

class BEntrenador(
    var id: Int,
    var nombre: String?,
    var descripcion: String?
){
    override fun toString(): String {
        return "${nombre} -  ${descripcion}"
    }
}