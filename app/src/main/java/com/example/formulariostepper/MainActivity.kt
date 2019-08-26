package com.example.formulariostepper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var fab: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.fab)
        fab!!.setOnClickListener{
            val intent = Intent(applicationContext, NuevoFormulario::class.java)
            startActivityForResult(intent, NEW_ALARM_REQUEST_CODE)
        }
    }
    companion object {

        val NEW_ALARM_REQUEST_CODE = 1

        private val DATA_RECEIVED = "data_received"
        private val INFORMATION = "information"
        private val DISCLAIMER = "disclaimer"
    }
}
