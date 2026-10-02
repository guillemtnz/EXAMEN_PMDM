package com.isengard.fraguas

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = "FraguasIsengard"
    private lateinit var etIdentificador: EditText
    private lateinit var cbAntorcha: CheckBox
    private lateinit var btnEnviar: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: Los cimientos de las fraguas se establecen")

        // Inicializar vistas
        etIdentificador = findViewById(R.id.etIdentificador)
        cbAntorcha = findViewById(R.id.cbAntorcha)
        btnEnviar = findViewById(R.id.btnEnviar)

        // Recuperar el estado del error si se ha girado la pantalla
        if (savedInstanceState != null) {
            val errorGuardado = savedInstanceState.getString("ERROR_ID")
            if (errorGuardado != null) {
                etIdentificador.error = errorGuardado
            }
        }

        // Control del foco
        // Al abrir, colocar el cursor en el EditText
        etIdentificador.requestFocus()
        //Si se cambia el foco y el EditText sigue vacío
        etIdentificador.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && etIdentificador.text.toString().isEmpty()) {
                etIdentificador.error = "El ejército no acepta soldados anónimos"
            }
        }

        // Evento del botón
        btnEnviar.setOnClickListener {
            val idUruk = etIdentificador.text.toString()

            if (idUruk.isEmpty()) {
                etIdentificador.error = "El ejército no acepta soldados anónimos"
            } else {
                // Envío correcto pero sin antorcha
                if (!cbAntorcha.isChecked) {
                    Log.e(TAG, "¡Peligro! Unidad enviada sin fuego")
                }

                // Toast
                Toast.makeText(
                    this,
                    "¡Unidad $idUruk enviada al Abismo de Helm!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    // Para que no se pierdan las cosas con el giro de pantalla
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Guardo el mensaje de error si existe
        if (etIdentificador.error != null) {
            outState.putString("ERROR_ID", etIdentificador.error.toString())
        }
    }

    //CICLO DE VIDA Y LOGS

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Las fraguas se encienden")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: La producción de Uruk-hai está a máximo rendimiento")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Saruman detiene la producción temporalmente")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Las fraguas se apagan y los orcos descansan")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Las fraguas de Isengard han sido destruidas por los Ents")
    }
}