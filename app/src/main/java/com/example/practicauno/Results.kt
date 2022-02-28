package com.example.practicauno

import android.app.Activity
import android.app.Instrumentation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicauno.databinding.ActivityMainBinding
import com.example.practicauno.databinding.ActivityResultsBinding

class Results : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val result = bundle?.getString("result")
        val operation = bundle?.getString("operation")

        binding.tvOperation.text = operation
        binding.tvResult.text = result
    }
}