package com.example.salud

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class Medicamentos: AppCompatActivity() {

    private lateinit var nombreMedicamento: EditText
    private lateinit var frecuenciaSpinner: Spinner
    private lateinit var diasSpinner: Spinner
    private lateinit var horaButton: Button
    private lateinit var fechaButton: Button
    private lateinit var tvHoraSeleccionada: TextView
    private lateinit var tvFechaInicio: TextView
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicamentos)

        // Vincular los elementos del XML con el código Kotlin
        nombreMedicamento = findViewById(R.id.nombre_medicamento)
        frecuenciaSpinner = findViewById(R.id.sp_horas)
        diasSpinner = findViewById(R.id.sp_dias)
        horaButton = findViewById(R.id.btn_seleccionar_hora)
        fechaButton = findViewById(R.id.btn_seleccionar_fecha_inicio)
        tvHoraSeleccionada = findViewById(R.id.tv_hora_seleccionada)
        tvFechaInicio = findViewById(R.id.tv_fecha_inicio)
        btnGuardar = findViewById(R.id.btn_guardar)

        // Llenar los spinners con opciones (puedes personalizar estas opciones)
        val horas = arrayOf("1 hora", "2 horas", "3 horas")
        val dias = arrayOf("1 día", "2 días", "3 días")

        val adapterHoras = ArrayAdapter(this, android.R.layout.simple_spinner_item, horas)
        adapterHoras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        frecuenciaSpinner.adapter = adapterHoras

        val adapterDias = ArrayAdapter(this, android.R.layout.simple_spinner_item, dias)
        adapterDias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        diasSpinner.adapter = adapterDias

        // Manejar la selección de la hora
        horaButton.setOnClickListener {
            showTimePicker()
        }

        // Manejar la selección de la fecha
        fechaButton.setOnClickListener {
            showDatePicker()
        }

        // Manejar el botón guardar
        btnGuardar.setOnClickListener {
            saveData()
        }
    }

    private fun showTimePicker() {
        // Aquí iría el código para mostrar un TimePickerDialog y actualizar la hora seleccionada
        // Este es solo un ejemplo simple con un valor ficticio
        val currentTime = "10:30"
        tvHoraSeleccionada.text = "Hora seleccionada: $currentTime"
    }

    private fun showDatePicker() {
        // Aquí iría el código para mostrar un DatePickerDialog y actualizar la fecha seleccionada
        // Este es solo un ejemplo simple con un valor ficticio
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        tvFechaInicio.text = "Fecha seleccionada: $currentDate"
    }

    private fun saveData() {
        val medicamento = nombreMedicamento.text.toString()
        val frecuencia = frecuenciaSpinner.selectedItem.toString()
        val dias = diasSpinner.selectedItem.toString()
        val hora = tvHoraSeleccionada.text.toString()
        val fecha = tvFechaInicio.text.toString()

        if (medicamento.isNotEmpty()) {
            Toast.makeText(this, "Datos guardados:\n$medicamento\n$frecuencia\n$dias\n$hora\n$fecha", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Por favor, ingresa el nombre del medicamento", Toast.LENGTH_SHORT).show()
        }
    }
}
