package com.example.salud

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USUARIOS_TABLE = """
        CREATE TABLE $TABLE_USUARIOS (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NOMBRE TEXT NOT NULL,
            $COLUMN_CORREO TEXT NOT NULL UNIQUE,
            $COLUMN_CONTRASENA TEXT NOT NULL
        );
        """
        db.execSQL(CREATE_USUARIOS_TABLE)

        val CREATE_CONTACTOS_TABLE = """
        CREATE TABLE $TABLE_CONTACTOS (
            $COLUMN_CONTACTO_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_USUARIO_ID INTEGER,
            $COLUMN_CONTACTO_NOMBRE TEXT NOT NULL,
            $COLUMN_CONTACTO_TEL TEXT NOT NULL,
            FOREIGN KEY ($COLUMN_USUARIO_ID) REFERENCES $TABLE_USUARIOS($COLUMN_ID) ON DELETE CASCADE
        );
        """
        db.execSQL(CREATE_CONTACTOS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(db)
    }

    fun insertarUsuario(nombre: String, correo: String, contrasena: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()

        values.put(COLUMN_NOMBRE, nombre)
        values.put(COLUMN_CORREO, correo)
        values.put(COLUMN_CONTRASENA, contrasena)

        val result = db.insert(TABLE_USUARIOS, null, values)
        db.close()

        return result != -1L
    }

    fun insertarContactoEmergencia(usuarioId: Int, nombre: String, telefono: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()

        // Guardar el contacto de emergencia
        values.put(COLUMN_USUARIO_ID, usuarioId)
        values.put(COLUMN_CONTACTO_NOMBRE, nombre)
        values.put(COLUMN_CONTACTO_TEL, telefono)

        val result = db.insert(TABLE_CONTACTOS, null, values)
        db.close()

        // Si result es -1, significa que hubo un error en la inserción
        return result != -1L
    }

    companion object {
        private const val DATABASE_NAME = "salud360.db"
        private const val DATABASE_VERSION = 1

        // Tablas
        const val TABLE_USUARIOS = "usuarios"
        const val TABLE_CONTACTOS = "contactos_de_emergencia"

        // Columnas de la tabla usuarios
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_CORREO = "correo"
        const val COLUMN_CONTRASENA = "contrasena"

        // Columnas de la tabla contactos de emergencia
        const val COLUMN_CONTACTO_ID = "id"
        const val COLUMN_USUARIO_ID = "usuario_id"
        const val COLUMN_CONTACTO_NOMBRE = "nombre"
        const val COLUMN_CONTACTO_TEL = "telefono"
    }
}

