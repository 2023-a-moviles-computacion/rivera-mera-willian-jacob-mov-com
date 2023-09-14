package com.example.examen_01.db

import com.example.examen_01.Motel
import com.google.firebase.firestore.FirebaseFirestore

class DbMotel(
    // Atributos
    private var idMotel: Int?,
    private var nombreMotel: String,
    private var ubicacion: String,
    private var fechaInicio: String,
    private var abierto: String,
    var fkPersona: Motel
) {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("Motel")

    fun insertMotel(): Boolean {
        // Create a new document with data
        val motel = hashMapOf(
            "nombreMotel" to nombreMotel,
            "ubicacion" to ubicacion,
            "abierto" to abierto,
            "fechaInicio" to fechaInicio,
            "fkPersona" to fkPersona
        )
        collection.add(motel)
        return true // You can add error logic if desired
    }

    fun getMotelById(id: String): DbMotel? {
        // This is an asynchronous method, you may need to use callbacks or coroutines
        var motel: DbMotel? = null
        collection.document(id).get().addOnSuccessListener { document ->
            if (document != null) {
                motel = document.toObject(DbMotel::class.java)
            }
        }
        return motel
    }

    fun updateMotel(id: String): Boolean {
        val motel = hashMapOf(
            "nombreMotel" to nombreMotel,
            "ubicacion" to ubicacion,
            "abierto" to abierto,
            "fechaInicio" to fechaInicio,
            "fkPersona" to fkPersona
        )
        collection.document(id).set(motel)
        return true // You can add error logic if desired
    }

    fun deleteMotel(id: String): Boolean {
        collection.document(id).delete()
        return true // You can add error logic if desired
    }


}