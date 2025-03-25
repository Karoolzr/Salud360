package com.example.salud

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Citas : AppCompatActivity() {

    // Definir las variables para los EditText y el Button
    private lateinit var etNombrePaciente: EditText
    private lateinit var etFechaCita: EditText
    private lateinit var etHoraCita: EditText
    private lateinit var etMotivoCita: EditText
    private lateinit var btnRegistrarCita: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citas)

        etNombrePaciente = findViewById(R.id.etNombrePaciente)
        etFechaCita = findViewById(R.id.etFechaCita)
        etHoraCita = findViewById(R.id.etHoraCita)
        etMotivoCita = findViewById(R.id.etMotivoCita)
        btnRegistrarCita = findViewById(R.id.btnRegistrarCita)

        btnRegistrarCita.setOnClickListener {
            registrarCita()
        }
    }

    private fun registrarCita() {
        // Obtener los datos de los EditText
        val nombrePaciente = etNombrePaciente.text.toString()
        val fechaCita = etFechaCita.text.toString()
        val horaCita = etHoraCita.text.toString()
        val motivoCita = etMotivoCita.text.toString()

        // Validar que los campos no estén vacíos
        if (nombrePaciente.isEmpty() || fechaCita.isEmpty() || horaCita.isEmpty() || motivoCita.isEmpty()) {
            Toast.makeText(this, "Por favor, llena todos los campos.", Toast.LENGTH_SHORT).show()
        } else {
            // Aquí iría la lógica para guardar los datos en la base de datos o en otro lugar
            Toast.makeText(this, "Cita registrada con éxito", Toast.LENGTH_SHORT).show()
        }
    }
}
