package com.example.salud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class IniciarSesion :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_iniciar_sesion)

        val correo = findViewById<EditText>(R.id.editTextCorreo)
        val contrasena = findViewById<EditText>(R.id.editTextContraseña)
        val registrate = findViewById<Button>(R.id.buttonRegistrate)
        val iniciarSesion = findViewById<Button>(R.id.buttonIniciarSesion)

        iniciarSesion.setOnClickListener{
            val correoTxt = correo.text.toString()
            val contrasenaTxt = contrasena.text.toString()

            if(correoTxt.isEmpty() || contrasenaTxt.isEmpty() )
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
        }

        registrate.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

    }
}