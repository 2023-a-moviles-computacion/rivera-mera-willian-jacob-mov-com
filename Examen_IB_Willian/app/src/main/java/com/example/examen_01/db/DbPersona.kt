package com.example.examen_01.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbPersona(
    //Atributos
    private var idPersona: Int?,
    private var nombrePersona: String,
    private var domicilio: String,
    private var vecesServicio: String,
    private var fechaUltima: String,
    private var fkMotel: Int,
    private val context: Context?
) {
    init {
        nombrePersona
        domicilio
        vecesServicio
        fechaUltima
        fkMotel
        context
    }

    fun setidPersona(idPersona: Int) {
        this.idPersona = idPersona
    }

    fun setnombrePersona(nombrePersona: String) {
        this.nombrePersona = nombrePersona
    }

    fun setDomicilio(domicilio: String) {
        this.domicilio = domicilio
    }

    fun setVecesServicio(vecesServicio: String) {
        this.vecesServicio = vecesServicio
    }

    fun setfechaUltima(fechaUltima: String) {
        this.fechaUltima = fechaUltima
    }

    fun setidMotel(fkMotel: Int) {
        this.fkMotel = fkMotel
    }

    fun getidPersona(): Int? {
        return idPersona
    }

    fun getidMotel(): Int {
        return fkMotel
    }

    fun getnombrePersona(): String {
        return nombrePersona
    }

    fun getDomicilio(): String {
        return domicilio
    }

    fun getVecesServicio(): String {
        return vecesServicio
    }

    fun getfechaUltima(): String {
        return fechaUltima
    }

    fun insertPersona(): Long {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_persona", this.nombrePersona)
        values.put("domicilio", this.domicilio)
        values.put("vecesServicio", this.vecesServicio)
        values.put("fechaUltima", this.fechaUltima)
        values.put("IDmotel", this.fkMotel)

        return db.insert("t_persona", null, values)
    }

    fun showPersonas(id: Int): ArrayList<DbPersona> {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var listaPersonas = ArrayList<DbPersona>()
        var tema: DbPersona
        var cursorPersonas: Cursor? = null

        // Ver si el id: Int es diferente de null
        cursorPersonas = db.rawQuery("SELECT * FROM t_tema WHERE IDasignatura=${id+1}", null)

        if (cursorPersonas.moveToFirst()) {
            do {
                tema = DbPersona(null, "", "", "", "", 0, null)

                tema.setidPersona(cursorPersonas.getString(0).toInt())
                tema.setnombrePersona(cursorPersonas.getString(1))
                tema.setDomicilio(cursorPersonas.getString(2))
                tema.setVecesServicio(cursorPersonas.getString(3))
                tema.setfechaUltima(cursorPersonas.getString(4))
                tema.setidMotel(cursorPersonas.getString(5).toInt())
                listaPersonas.add(tema)
            } while (cursorPersonas.moveToNext())
        }

        cursorPersonas.close()
        return listaPersonas
    }

    fun getPersonaById(id: Int): DbPersona{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var persona = DbPersona(null, "", "", "", "",0, this.context)
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_persona WHERE id_persona = ${id+1}", null)

        if (cursor.moveToFirst()) {
            do {
                persona.setidPersona(cursor.getString(0).toInt())
                persona.setnombrePersona(cursor.getString(1))
                persona.setDomicilio(cursor.getString(2))
                persona.setVecesServicio(cursor.getString(3))
                persona.setfechaUltima(cursor.getString(4))
                persona.setidMotel(cursor.getString(5).toInt())
            } while (cursor.moveToNext())
        }

        cursor.close()
        return persona
    }

    fun updatePersona(): Int {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_persona", this.nombrePersona)
        values.put("domicilio", this.domicilio)
        values.put("vecesServicio", this.vecesServicio)
        values.put("fechaUltima", this.fechaUltima)
        values.put("IDmotel", this.fkMotel)

        return db.update("t_persona", values, "id_persona="+this.idPersona, null)
    }

    fun deletePersona(id: Int): Int{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_persona", "id_persona="+(id+1), null)
    }

    override fun toString(): String {
        val salida =
            "Número de persona: ${idPersona}\n" +
                    "Cliente: ${nombrePersona}\n" +
                    "Domicilio: ${domicilio}\n" +
                    "Veces que ha usado el servicio: ${vecesServicio} \n" +
                    "Fecha de ultima visita: ${fechaUltima}\n" +
                    "Id Motel: ${fkMotel}"

        return salida
    }
}