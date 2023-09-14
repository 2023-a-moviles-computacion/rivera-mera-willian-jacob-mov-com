package com.example.examen_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examen_01.db.DbMotel

class ActualizarMotel : AppCompatActivity() {
    // Declaración de vistas y variables
    private lateinit var btn_actualizar_motel: Button
    private lateinit var et_nombreMotel: EditText
    private lateinit var et_ubicacion: EditText
    private lateinit var et_abierto: EditText
    private lateinit var et_fechaInicio: EditText
    private lateinit var et_fkPersona: EditText
    private var idMotelSeleccionado: Int = -1  // Valor por defecto para identificar si no se ha establecido

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_motel)

        // Inicialización de vistas
        btn_actualizar_motel = findViewById(R.id.btn_upd_Motel)
        et_nombreMotel = findViewById(R.id.et_upd_nombMot)
        et_ubicacion = findViewById(R.id.et_upd_ubicacionMot)
        et_abierto = findViewById(R.id.et_upd_estadoMot)
        et_fechaInicio = findViewById(R.id.et_upd_fechaMot)


        // Obtener el ID del tema seleccionado, suponiendo que lo pasas a través de un Intent con la clave "ID_TEMA"
        idMotelSeleccionado = intent.getIntExtra("ID_MOTEL", -1)

        // Evento de clic para el botón de actualizar
        btn_actualizar_motel.setOnClickListener {
            val motel = DbMotel(
                null,
                et_nombreMotel.text.toString(),
                et_ubicacion.text.toString(),
                et_abierto.text.toString(),  // No convertimos a Double ya que en DbTema es String
                et_fechaInicio.text.toString(),
                et_fkPersona.text.toString().toInt()
            )

            if (motel.updateMotel(idMotelSeleccionado.toString())) {
                Toast.makeText(this, "Motel Actualizado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show()
            }
        }
    }

}