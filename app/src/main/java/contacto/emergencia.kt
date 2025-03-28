package com.example.emergencycontact

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.salud.R

class emergencia: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacto_emergencia)

        // Referencias a los elementos de la UI
        val etNombre = findViewById<EditText>(R.id.editTextNombre2)
        val etTelefono = findViewById<EditText>(R.id.editTextCorreo2)
        val btnRegistrar = findViewById<Button>(R.id.buttonRegistrarse)

        // Evento click del botón
        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()

            if (nombre.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Contacto registrado: $nombre - $telefono", Toast.LENGTH_LONG).show()
                etNombre.text.clear()
                etTelefono.text.clear()
            }
        }
    }
}
