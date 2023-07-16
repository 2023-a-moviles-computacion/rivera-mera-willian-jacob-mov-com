package com.example.examen_01.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbMotel(
    // Atributos
    private var idMotel: Int?,
    private var nombreMotel: String,
    private var ubicacion: String,
    private var fechaInicio: String,
    private var abierto: String,
    private val context: Context?
) {
    init {
        idMotel
        nombreMotel
        ubicacion
        fechaInicio
        abierto
        context
    }

    fun setidMotel(idMotel: Int) {
        this.idMotel = idMotel
    }

    fun setnombreMotel(nombreMotel: String) {
        this.nombreMotel = nombreMotel
    }

    fun setUbicacion(ubicacion: String) {
        this.ubicacion = ubicacion
    }

    fun setfechaInicio(fechaPublicacion: String) {
        this.fechaInicio = fechaPublicacion
    }

    fun setAbierto(abierto: String) {
        this.abierto = abierto
    }

    fun getidMotel(): Int? {
        return idMotel
    }

    fun getnombreMotel(): String {
        return nombreMotel
    }

    fun getUbicacion(): String {
        return ubicacion
    }

    fun getfechaInicio(): String {
        return fechaInicio
    }

    fun getAbierto(): String {
        return abierto
    }

    fun insertMotel(): Long {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_motel", this.nombreMotel)
        values.put("ubicacion", this.ubicacion)
        values.put("fechaPublicacion", this.fechaInicio)
        values.put("abierto", this.abierto)

        return db.insert("t_Motel", null, values)
    }

    fun showMoteles(): ArrayList<DbMotel> {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var lista = ArrayList<DbMotel>()
        var motel: DbMotel
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_asignatura", null)

        if (cursor.moveToFirst()) {
            do {
                motel = DbMotel(null, "", "", "", "", null)

                motel.setidMotel(cursor.getString(0).toInt())
                motel.setnombreMotel(cursor.getString(1))
                motel.setUbicacion(cursor.getString(2))
                motel.setfechaInicio(cursor.getString(3))
                motel.setAbierto(cursor.getString(4))
                lista.add(motel)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

    fun getMotelById(id: Int): DbMotel{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var motel = DbMotel(null, "", "", "", "", this.context)
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_motel WHERE id_motel = ${id+1}", null)

        if (cursor.moveToFirst()) {
            do {
                motel.setidMotel(cursor.getString(0).toInt())
                motel.setnombreMotel(cursor.getString(1))
                motel.setUbicacion(cursor.getString(2))
                motel.setfechaInicio(cursor.getString(3))
                motel.setAbierto(cursor.getString(4))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return motel
    }

    fun updateMotel(): Int {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_motel", this.nombreMotel)
        values.put("ubicacion", this.ubicacion)
        values.put("fechaPublicacion", this.fechaInicio)
        values.put("abierto", this.abierto)

        return db.update("t_motel", values, "id_motel="+this.idMotel, null)
    }

    fun deleteMotel(id: Int): Int{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_motel", "id_motel="+(id+1), null)
    }

    override fun toString(): String {
        val salida =
            "Id Motel: ${idMotel}\n" +
                    "Motel: ${nombreMotel}\n" +
                    "Ubicacion: ${ubicacion}\n" +
                    "Fecha de actualizacion: ${fechaInicio}\n" +
                    "Se encuentra abierto?: ${abierto}"

        return salida
    }
}