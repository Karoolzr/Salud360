package com.example.salud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val agregarMedicamentos = findViewById<Button>(R.id.buttonAgregarMedicamentos)
        val agregarSignos = findViewById<Button>(R.id.buttonAgregarSignos)
        val agregarCitas = findViewById<Button>(R.id.buttonAgregarCitas)
        val verHistorial = findViewById<Button>(R.id.buttonVerHistorial)
        val agregarContacto = findViewById<Button>(R.id.buttonAgregarContacto)

        agregarMedicamentos.setOnClickListener {
            val intent = Intent(this, Medicamentos::class.java)
            startActivity(intent)
        }

        agregarSignos.setOnClickListener {
            val intent = Intent(this, Signos::class.java)
            startActivity(intent)
        }

        agregarCitas.setOnClickListener {
            val intent = Intent(this, Citas::class.java)
            startActivity(intent)
        }

        verHistorial.setOnClickListener {
            val intent = Intent(this, Historial::class.java)
            startActivity(intent)
        }

        agregarContacto.setOnClickListener {
            val intent = Intent(this, ContactoEmergencia::class.java)
            startActivity(intent)
        }
    }
}
