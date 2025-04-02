package com.example.salud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class IniciarSesion : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_iniciar_sesion)

        // Revisar si ya hay una sesión activa
        val sharedPreferences = getSharedPreferences("Sesion", MODE_PRIVATE)
        val correoGuardado = sharedPreferences.getString("correo", null)
        val contrasenaGuardada = sharedPreferences.getString("contrasena", null)

        if (correoGuardado != null && contrasenaGuardada != null) {
            // Si ya hay una sesión activa, redirigir automáticamente al menú
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish() // Terminar la actividad de inicio de sesión para no volver atrás
        }

        // Inicializar la base de datos
        dbHelper = DatabaseHelper(this)

        val correo = findViewById<EditText>(R.id.editTextCorreo)
        val contrasena = findViewById<EditText>(R.id.editTextContraseña)
        val registrate = findViewById<Button>(R.id.buttonRegistrate)
        val iniciarSesion = findViewById<Button>(R.id.buttonIniciarSesion)

        // Acción para iniciar sesión
        iniciarSesion.setOnClickListener {
            val correoTxt = correo.text.toString()
            val contrasenaTxt = contrasena.text.toString()

            if (correoTxt.isEmpty() || contrasenaTxt.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Verificar las credenciales
                if (verificarCredenciales(correoTxt, contrasenaTxt)) {
                    // Si la autenticación es exitosa, guardar la sesión
                    val sharedPreferences = getSharedPreferences("Sesion", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("correo", correoTxt)
                    editor.putString("contrasena", contrasenaTxt)
                    editor.apply()  // Guardar los cambios

                    // Redirigir a la siguiente actividad
                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)
                    finish()  // Cerrar la pantalla de login para no permitir retroceder
                } else {
                    // Si las credenciales no son correctas
                    Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Acción para registrar un nuevo usuario
        registrate.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }

    // Método para verificar las credenciales
    private fun verificarCredenciales(correo: String, contrasena: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_USUARIOS,
            arrayOf(DatabaseHelper.COLUMN_ID),
            "${DatabaseHelper.COLUMN_CORREO} = ? AND ${DatabaseHelper.COLUMN_CONTRASENA} = ?",
            arrayOf(correo, contrasena),
            null,
            null,
            null
        )

        val esValido = cursor.count > 0
        cursor.close()
        db.close()

        return esValido
    }
}


