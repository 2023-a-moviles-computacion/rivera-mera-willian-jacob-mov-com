package com.example.examen_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.examen_01.db.DbPersona

class Persona : AppCompatActivity() {
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persona)

        val nombre = findViewById<EditText>(R.id.editText_persona)
        nombre.requestFocus()
        val domicilio = findViewById<EditText>(R.id.editText_domicilio)
        val veces = findViewById<EditText>(R.id.editText_vecesServicio)
        val fecha = findViewById<EditText>(R.id.editText_fecha)
        val idMotel = findViewById<EditText>(R.id.editText_idmotel)

        val btnInsertar = findViewById<Button>(R.id.btn_insert)
        btnInsertar.setOnClickListener {
            val persona: DbPersona = DbPersona(
                null,
                nombre.text.toString(),
                domicilio.text.toString(),
                veces.text.toString(),
                fecha.text.toString(),
                idMotel.text.toString().toInt(),
                this
            )
            val resultado = persona.insertPersona()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL INSERTAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        val nombre = findViewById<EditText>(R.id.editText_persona)
        nombre.setText("")
        val ubicacion = findViewById<EditText>(R.id.editText_domicilio)
        ubicacion.setText("")
        val veces = findViewById<EditText>(R.id.editText_vecesServicio)
        veces.setText("")
        val fecha = findViewById<EditText>(R.id.editText_fecha)
        fecha.setText("")
        val idMotel = findViewById<EditText>(R.id.editText_idmotel)
        idMotel.setText("")
        nombre.requestFocus()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menupersona, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_motel -> {
                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar_motel -> {
                //abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}