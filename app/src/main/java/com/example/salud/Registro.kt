package com.example.salud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Registro : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        // Inicializar vistas
        val nombre = findViewById<EditText>(R.id.editTextNombre)
        val correo = findViewById<EditText>(R.id.editTextCorreo)
        val contrasena = findViewById<EditText>(R.id.editTextContraseña)
        val confContrasena = findViewById<EditText>(R.id.editTextContraseña2)
        val registrarse = findViewById<Button>(R.id.buttonRegistrarse)
        val iniciarSesion = findViewById<Button>(R.id.buttonIniciarSesion)

        // Inicializar el helper para la base de datos
        dbHelper = DatabaseHelper(this)

        registrarse.setOnClickListener {
            val nombreTxt = nombre.text.toString()
            val correoTxt = correo.text.toString()
            val contrasenaTxt = contrasena.text.toString()
            val confContrasenaTxt = confContrasena.text.toString()

            // Validaciones
            if (nombreTxt.isEmpty() || correoTxt.isEmpty() || contrasenaTxt.isEmpty() || confContrasenaTxt.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (contrasenaTxt != confContrasenaTxt) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (contrasenaTxt.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Insertar usuario en la base de datos
            val usuarioRegistrado = dbHelper.insertarUsuario(
                nombreTxt,
                correoTxt,
                contrasenaTxt
            )

            if (usuarioRegistrado) {
                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                // Limpiar los campos
                nombre.text.clear()
                correo.text.clear()
                contrasena.text.clear()
                confContrasena.text.clear()

                // Redirigir a la pantalla de inicio de sesión
                val intent = Intent(this, IniciarSesion::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Hubo un error al registrar el usuario", Toast.LENGTH_SHORT).show()
            }
        }

        iniciarSesion.setOnClickListener {
            // Redirigir a la pantalla de inicio de sesión
            val intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
        }
    }
}
