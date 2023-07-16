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

class Motel : AppCompatActivity() {
    companion object{
        var idMotelSeleccionado = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motel)
        showListViewMotel()

        val nombre = findViewById<EditText>(R.id.editText_nombreMot)
        nombre.requestFocus()
        val ubicacion = findViewById<EditText>(R.id.editText_ubicacionMot)
        val fecha = findViewById<EditText>(R.id.editText_fechaMot)
        val abierto = findViewById<EditText>(R.id.editText_estadoMot)

        val btnInsertar = findViewById<Button>(R.id.btn_insertarMot)
        btnInsertar.setOnClickListener {
            val motel = DbMotel(
                null,
                nombre.text.toString(),
                ubicacion.text.toString(),
                fecha.text.toString(),
                abierto.text.toString(),
                this
            )
            val resultado = motel.insertMotel()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
                showListViewMotel()
            } else {
                Toast.makeText(this, "ERROR EN INSERTAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        val nombre = findViewById<EditText>(R.id.editText_nombreMot)
        nombre.setText("")
        val ubicacion = findViewById<EditText>(R.id.editText_ubicacionMot)
        ubicacion.setText("")
        val fecha = findViewById<EditText>(R.id.editText_fechaMot)
        fecha.setText("")
        val estado = findViewById<EditText>(R.id.editText_estadoMot)
        estado.setText("")
        nombre.requestFocus()
    }

    fun showListViewMotel() {
        // ListView Temaes
        val motel = DbMotel(null, "", "", "", "", this)
        val listView = findViewById<ListView>(R.id.listview_motel)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            motel.showMoteles()
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menumotel, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idMotelSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_motel -> {
                irActividad(ActualizarMotel::class.java)
                return true
            }
            R.id.mi_eliminar_motel -> {
                abrirDialogo()
                return true
            }
            R.id.mi_verpersonas -> {
                irActividad(verPersonas::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar este Motel?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                val asignatura = DbMotel(null, "", "", "", "", this)
                val resultado = asignatura.deleteMotel(idMotelSeleccionado)
                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                    showListViewMotel()
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }
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