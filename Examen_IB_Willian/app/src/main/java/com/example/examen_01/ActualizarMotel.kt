package com.example.examen_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examen_01.db.DbMotel

class ActualizarMotel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_motel)

        val idMotel = Motel.idMotelSeleccionado
        var motel = DbMotel(null, "", "", "", "", this)
        motel = motel.getMotelById(idMotel)

        var id = findViewById<EditText>(R.id.et_upd_idMot)
        id.setText(motel.getidMotel().toString())
        var nombre = findViewById<EditText>(R.id.et_upd_nombMot)
        nombre.setText(motel.getnombreMotel())
        var ubicacion = findViewById<EditText>(R.id.et_upd_ubicacionMot)
        ubicacion.setText(motel.getUbicacion())
        var fecha = findViewById<EditText>(R.id.et_upd_fechaMot)
        fecha.setText(motel.getfechaInicio())
        var abierto = findViewById<EditText>(R.id.et_upd_estadoMot)
        abierto.setText(motel.getAbierto())

        val btn_actualizarMotel = findViewById<Button>(R.id.btn_upd_Motel)
        btn_actualizarMotel.setOnClickListener {
            // Motel actualizado
            motel.setnombreMotel(nombre.text.toString())
            motel.setUbicacion(ubicacion.text.toString())
            motel.setfechaInicio(fecha.text.toString())
            motel.setAbierto(abierto.text.toString())
            val resultado = motel.updateMotel()

            if (resultado > 0) {
                Toast.makeText(this, "DATOS ACTUALIZADOS", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR: NO SE PUDO ACTUALIZAR", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        val id = findViewById<EditText>(R.id.et_upd_idMot)
        id.setText("")
        val nombre = findViewById<EditText>(R.id.et_upd_nombMot)
        nombre.setText("")
        val ubicacion = findViewById<EditText>(R.id.et_upd_ubicacionMot)
        ubicacion.setText("")
        val fecha = findViewById<EditText>(R.id.et_upd_fechaMot)
        fecha.setText("")
        val abierto = findViewById<EditText>(R.id.et_upd_estadoMot)
        abierto.setText("")
        id.requestFocus()
    }

}