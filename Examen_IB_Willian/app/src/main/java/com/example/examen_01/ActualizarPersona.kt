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

        val idPersona = verPersonas.idPersonaSeleccionada
        var tema = DbPersona(null, "", "", "", "", 0, this)
        tema = tema.getPersonaById(idPersona)

        var id = findViewById<EditText>(R.id.et_upd_idPersona)
        id.setText(tema.getidPersona().toString())
        var nombre = findViewById<EditText>(R.id.et_upd_nombPersona)
        nombre.setText(tema.getnombrePersona())
        var domicilio = findViewById<EditText>(R.id.et_upd_domicilioPersona)
        domicilio.setText(tema.getDomicilio())
        var veces = findViewById<EditText>(R.id.et_upd_vecesServicio)
        veces.setText(tema.getVecesServicio())
        var fecha = findViewById<EditText>(R.id.et_upd_fechaTema)
        fecha.setText(tema.getfechaUltima())
        var fk = findViewById<EditText>(R.id.et_upd_idAlbPersona)
        fk.setText(tema.getidMotel().toString())

        val btn_actualizar_persona = findViewById<Button>(R.id.btn_upd_Persona)
        btn_actualizar_persona.setOnClickListener {
            tema.setnombrePersona(nombre.text.toString())
            tema.setDomicilio(domicilio.text.toString())
            tema.setVecesServicio(veces.text.toString())
            tema.setfechaUltima(fecha.text.toString())
            tema.setidMotel(fk.text.toString().toInt())
            val resultado = tema.updatePersona()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        var id = findViewById<EditText>(R.id.et_upd_idPersona)
        id.setText("")
        var nombre = findViewById<EditText>(R.id.et_upd_nombPersona)
        nombre.setText("")
        var domicilio = findViewById<EditText>(R.id.et_upd_domicilioPersona)
        domicilio.setText("")
        var veces = findViewById<EditText>(R.id.et_upd_vecesServicio)
        veces.setText("")
        var fecha = findViewById<EditText>(R.id.et_upd_fechaTema)
        fecha.setText("")
        var fk = findViewById<EditText>(R.id.et_upd_idAlbPersona)
        fk.setText("")
        id.requestFocus()
    }
}