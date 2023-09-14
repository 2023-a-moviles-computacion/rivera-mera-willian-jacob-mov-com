package com.example.examen_01

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.examen_01.db.DbMotel
import com.example.examen_01.db.DbPersona

class verPersonas : AppCompatActivity() {
    companion object {
        var idPersonaSeleccionada = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_personas)
        val idMotel = Motel.idMotelSeleccionado
        var motelAux = DbMotel(null, "", "", "", "", this)

        val textViewDomicilio = findViewById<TextView>(R.id.tv_motelesVerPersonas)
        textViewDomicilio.text = motelAux.getMotelById(idMotel).getnombreMotel()

        val btnCrearPersona = findViewById<Button>(R.id.btn_crearPersona)
        btnCrearPersona.setOnClickListener {
            irActividad(Persona::class.java)
        }
        showListView(idMotel)
    }

    fun showListView(id: Int) {
        val objetoPersona = DbPersona(null, "", "", "", "", 0, this)
        val listViewPersonas = findViewById<ListView>(R.id.listView_persona)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            objetoPersona.showPersonas(id)
        )
        listViewPersonas.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listViewPersonas)
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
        idPersonaSeleccionada = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_motel -> {
                irActividad(ActualizarPersona::class.java)
                return true
            }
            R.id.mi_eliminar_motel -> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar a esta persona?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                val persona = DbPersona(null, "", "", "", "", 0, this)
                val resultado = persona.deletePersona(idPersonaSeleccionada)
                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }
                val idMotel = Motel.idMotelSeleccionado
                showListView(idMotel)
            }
        )
        builder.setNegativeButton(
            "NO",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }


}