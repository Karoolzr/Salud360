package com.example.salud

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContactoEmergencia : AppCompatActivity() {

    private lateinit var editTextNombre: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var buttonRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacto_emergencia)

        editTextNombre = findViewById(R.id.editTextNombre2)
        editTextTelefono = findViewById(R.id.editTextCorreo2)
        buttonRegistrar = findViewById(R.id.buttonRegistrarse)

        buttonRegistrar.setOnClickListener {
            registerEmergencyContact()
        }
    }

    private fun registerEmergencyContact() {
        val nombre = editTextNombre.text.toString()
        val telefono = editTextTelefono.text.toString()

        if (nombre.isNotEmpty() && telefono.isNotEmpty()) {

            Toast.makeText(this, "Contacto de emergencia registrado:\n$nombre\n$telefono", Toast.LENGTH_SHORT).show()
        } else {

            Toast.makeText(this, "Por favor, llena todos los campos.", Toast.LENGTH_SHORT).show()
        }
    }
}
