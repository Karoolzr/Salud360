package com.example.salud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Registro :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        val nombre = findViewById<EditText>(R.id.editTextNombre2)
        val correo = findViewById<EditText>(R.id.editTextCorreo2)
        val contrasena =  findViewById<EditText>(R.id.editTextContraseña2)
        val confContrasena =  findViewById<EditText>(R.id.editTextConfirmarContraseña)
        val registrarse = findViewById<Button>(R.id.buttonRegistrarse)
        val iniciarSesion = findViewById<Button>(R.id.buttonIniciarSesion)

        registrarse.setOnClickListener {
            val nombreTxt = nombre.text.toString()
            val correoTxt = correo.text.toString()
            val contrasenaTxt = contrasena.text.toString()
            val confContrasenaTxt = confContrasena.text.toString()

            if (nombreTxt.isEmpty() || correoTxt.isEmpty() || contrasenaTxt.isEmpty() || confContrasenaTxt.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else if (contrasenaTxt != confContrasenaTxt) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            } else if (contrasenaTxt.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            } else {
                //registrarUsuario(nombreTxt, correoTxt, contraseñaTxt)
            }
        }

        iniciarSesion.setOnClickListener {
            val intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
        }
    }


}