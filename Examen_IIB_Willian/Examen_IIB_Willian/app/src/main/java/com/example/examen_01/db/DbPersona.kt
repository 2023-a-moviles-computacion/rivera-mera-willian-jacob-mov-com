package com.example.examen_01.db

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore

class DbPersona(
    //Atributos
    private var idPersona: Int?,
    private var nombrePersona: String,
    private var domicilio: String,
    private var vecesServicio: String,
    private var fechaUltima: String,

) {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("Persona")


    fun insertPersona(): Boolean {
        val persona = hashMapOf(
            "nombrePersona" to nombrePersona,
            "domicilio" to domicilio,
            "fechaUltima" to fechaUltima,
            "vecesServicio" to vecesServicio
        )
        collection.add(persona)
        return true
    }

    fun getPersonaById(id: String): DbPersona? {
        var Persona: DbPersona? = null
        collection.document(id).get().addOnSuccessListener { document ->
            if (document != null) {
                Persona = document.toObject(DbPersona::class.java)
            }
        }
        return Persona
    }

    fun updatePersona(id: String): Boolean {
        val persona = hashMapOf(
            "nombrePersona" to nombrePersona,
            "domicilio" to domicilio,
            "fechaUltima" to fechaUltima,
            "vecesServicio" to vecesServicio
        )
        collection.document(id).set(persona)
        return true
    }

    fun deletePersona(id: String): Boolean {
        collection.document(id).delete()
        return true
    }
}
