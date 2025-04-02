package com.example.salud

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Menu : AppCompatActivity() {

    private val PERMISSION_REQUEST_CALL_PHONE = 1
    private val PERMISSION_REQUEST_SEND_SMS = 2
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        dbHelper = DatabaseHelper(this) // Inicializar la base de datos correctamente

        val agregarMedicamentos = findViewById<ImageButton>(R.id.buttonAgregarMedicamentos)
        val agregarSignos = findViewById<ImageButton>(R.id.buttonAgregarSignos)
        val agregarCitas = findViewById<ImageButton>(R.id.buttonAgregarCitas)
        val agregarContacto = findViewById<ImageButton>(R.id.buttonAgregarContacto)
        val botonEmergencia = findViewById<ImageButton>(R.id.imageButton3)

        agregarMedicamentos.setOnClickListener {
            startActivity(Intent(this, Medicamentos::class.java))
        }

        agregarSignos.setOnClickListener {
            startActivity(Intent(this, Signos::class.java))
        }

        agregarCitas.setOnClickListener {
            startActivity(Intent(this, Citas::class.java))
        }

        agregarContacto.setOnClickListener {
            startActivity(Intent(this, ContactoEmergencia::class.java))
        }

        botonEmergencia.setOnClickListener {
            val numeroEmergencia = obtenerNumeroEmergencia()

            if (numeroEmergencia.isNotEmpty()) {
                Log.d("Menu", "Número de emergencia obtenido: $numeroEmergencia")
                enviarSMS(numeroEmergencia, "\uD83C\uDD98Necesito asistencia médica urgente")
                llamarAl911()
            } else {
                Toast.makeText(this, "No hay número de emergencia registrado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun obtenerNumeroEmergencia(): String {
        var numeroEmergencia = ""
        try {
            val db: SQLiteDatabase = dbHelper.readableDatabase
            val query = "SELECT ${DatabaseHelper.COLUMN_CONTACTO_TEL} FROM ${DatabaseHelper.TABLE_CONTACTOS} LIMIT 1"
            val cursor: Cursor = db.rawQuery(query, null)

            if (cursor.moveToFirst()) {
                numeroEmergencia = cursor.getString(0)
            }

            cursor.close()
            db.close()
        } catch (e: Exception) {
            Log.e("Menu", "Error al obtener número de emergencia: ${e.message}")
        }
        return numeroEmergencia
    }


    private fun enviarSMS(numero: String, mensaje: String) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.SEND_SMS),
                PERMISSION_REQUEST_SEND_SMS
            )
        } else {
            try {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(numero, null, mensaje, null, null)
                Toast.makeText(this, "Mensaje de emergencia enviado", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al enviar SMS", Toast.LENGTH_SHORT).show()
                Log.e("Menu", "Error al enviar SMS: ${e.message}")
            }
        }
    }

    private fun llamarAl911() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSION_REQUEST_CALL_PHONE
            )
        } else {
            try {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:911"))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error al intentar llamar", Toast.LENGTH_SHORT).show()
                Log.e("Menu", "Error al llamar al 911: ${e.message}")
            }
        }
    }
}
