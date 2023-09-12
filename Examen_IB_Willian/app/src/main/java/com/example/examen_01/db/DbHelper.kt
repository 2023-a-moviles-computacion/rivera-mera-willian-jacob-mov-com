package com.example.examen_01.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?) : SQLiteOpenHelper(
    context,
    "DBExamen01.db",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaPersona =
            "CREATE TABLE t_persona(" +
                    "id_persona INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_persona TEXT NOT NULL," +
                    "domicilio TEXT NOT NULL," +
                    "vecesServicio TEXT NOT NULL," +
                    "fechaUltima TEXT NOT NULL);"

        val scriptSQLCrearTablaMotel =
            "CREATE TABLE t_Motel(" +
                    "id_motel INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_motel TEXT NOT NULL," +
                    "ubicacion TEXT NOT NULL," +
                    "abierto TEXT NOT NULL," +
                    "fechaInicio TEXT NOT NULL, " +
                    "IDpersona INTEGER NOT NULL," +
                    "FOREIGN KEY(IDasignatura) REFERENCES t_persona(id_persona));"

        db?.execSQL(scriptSQLCrearTablaPersona)
        db?.execSQL(scriptSQLCrearTablaMotel)
    }

    // Se ejecuta cuando la version cambia
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS t_Motel")
        db?.execSQL("DROP TABLE IF EXISTS t_persona")
        onCreate(db)
    }
}