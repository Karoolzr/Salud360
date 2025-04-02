package com.example.salud

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContactoEmergencia : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacto_emergencia)

        dbHelper = DatabaseHelper(this)

        val nombreEmergencia = findViewById<EditText>(R.id.editTextNombre2)
        val telefonoEmergencia = findViewById<EditText>(R.id.editTextNumerodetelefono)
        val botonGuardar = findViewById<Button>(R.id.buttonGuardar2)

        telefonoEmergencia.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                if (editable != null && editable.length > 10) {
                    telefonoEmergencia.setText(editable.substring(0, 10))
                    telefonoEmergencia.setSelection(10)
                }
            }
        })

        botonGuardar.setOnClickListener {
            val nombre = nombreEmergencia.text.toString().trim()
            val telefono = telefonoEmergencia.text.toString().trim()
            val usuarioId = obtenerUsuarioId()


            if (nombre.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else if (telefono.length != 10) {
                Toast.makeText(this, "El número de teléfono debe tener 10 dígitos", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("ContactoEmergencia", "Intentando guardar contacto... UsuarioID: $usuarioId")

                val contactoGuardado = dbHelper.insertarContactoEmergencia(usuarioId, nombre, telefono)

                if (contactoGuardado) {
                    Toast.makeText(this, "Contacto de emergencia guardado", Toast.LENGTH_SHORT).show()
                    nombreEmergencia.text.clear()
                    telefonoEmergencia.text.clear()
                } else {
                    Toast.makeText(this, "Error al guardar el contacto", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun obtenerUsuarioId(): Int {
        val sharedPrefs = getSharedPreferences("usuario", MODE_PRIVATE)
        return sharedPrefs.getInt("usuarioId", -1)
    }
}
