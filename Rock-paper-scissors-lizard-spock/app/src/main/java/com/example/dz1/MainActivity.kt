package com.example.dz1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonNext = findViewById<Button>(R.id.buttonNext)
        val namePlayer = findViewById<EditText>(R.id.editTextName)
        buttonNext.setOnClickListener {
            if(namePlayer.getText().toString()!=""){
                val intent = Intent(this@MainActivity, Step::class.java)
                intent.putExtra("Username", namePlayer.getText().toString())
                startActivity(intent)
            }
            else
                Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show()
        }
    }
}