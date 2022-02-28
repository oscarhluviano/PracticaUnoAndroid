package com.example.practicauno

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practicauno.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = getString(R.string.select)

        val intent = Intent(this, Results::class.java)

        spinner = findViewById(R.id.spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(spinner.selectedItem.toString()){
                    getString(R.string.voltaje) -> {
                        binding.etNumber.hint = getString(R.string.corriente)
                        binding.etNumber2.hint = getString(R.string.resistencia)
                        binding.imageView.setImageResource(R.drawable.voltaje)
                    }
                    getString(R.string.corriente) -> {
                        binding.etNumber.hint = getString(R.string.voltaje)
                        binding.etNumber2.hint = getString(R.string.resistencia)
                        binding.imageView.setImageResource(R.drawable.corriente)
                    }
                    getString(R.string.resistencia) -> {
                        binding.etNumber.hint = getString(R.string.voltaje)
                        binding.etNumber2.hint = getString(R.string.corriente)
                        binding.imageView.setImageResource(R.drawable.resistencia)
                    }

                }
            }

        }

        binding.button.setOnClickListener {
            if(validaCampos()) {
                val params = Bundle()
                if (calculate().toString() != "-1.0") {
                    params.putString("result", calculate().toString())
                    params.putString("operation", spinner.selectedItem.toString())
                    intent.putExtras(params)
                    startActivity(intent)
                }
            }else Toast.makeText(this, getString(R.string.warning),Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculate(): Double {

            val result:Double
            when (spinner.selectedItem.toString()) {
                getString(R.string.voltaje) ->{
                    result =
                        (binding.etNumber.text.toString().toDouble()*binding.etNumber2.text.toString().toDouble())
                    return result
                }
                getString(R.string.corriente) ->{
                    if (binding.etNumber2.text.toString() != "0"){
                    result =
                        (binding.etNumber.text.toString().toDouble()/binding.etNumber2.text.toString().toDouble())
                    return result
                    }else Toast.makeText(this, getText(R.string.warningOp), Toast.LENGTH_SHORT).show()
                }
                getString(R.string.resistencia) ->{
                    if (binding.etNumber2.text.toString() != "0"){
                    result =
                        (binding.etNumber.text.toString().toDouble()/binding.etNumber2.text.toString().toDouble())
                    return result
                    }else Toast.makeText(this, getText(R.string.warningOp), Toast.LENGTH_SHORT).show()
                }
            }
        return -1.0
    }

    private fun validaCampos():Boolean{
        return !(binding.etNumber.text.toString() == "" && binding.etNumber2.text.toString() == "")
    }
}