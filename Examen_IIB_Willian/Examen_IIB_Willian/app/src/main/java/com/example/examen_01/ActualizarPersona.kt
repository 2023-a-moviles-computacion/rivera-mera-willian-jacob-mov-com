package com.example.examen_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examen_01.db.DbPersona

class ActualizarPersona : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_persona)

        val btn_actualizarPersona: Button = findViewById(R.id.btn_upd_Persona)
        val et_nombrePersona: EditText = findViewById(R.id.et_upd_nombPersona)
        val et_domicilio: EditText = findViewById(R.id.et_upd_domicilioPersona)
        val et_fechaUltima: EditText = findViewById(R.id.et_upd_fechaTema)
        val et_vecesServicio: EditText = findViewById(R.id.et_upd_vecesServicio)
        var idPersonaSeleccionada: Int = -1  // Valor por defecto para identificar si no se ha establecido

        btn_actualizarPersona.setOnClickListener {
            val persona = DbPersona(
                null,
                et_nombrePersona.text.toString(),
                et_domicilio.text.toString(),
                et_fechaUltima.text.toString(),
                et_vecesServicio.text.toString()
            )

            if (persona.updatePersona(idPersonaSeleccionada.toString())) {
                Toast.makeText(this, "Persona actualizada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show()
            }
        }


    }


}