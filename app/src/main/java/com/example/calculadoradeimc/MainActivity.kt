package com.example.calculadoradeimc

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.DecimalFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCalc.setOnClickListener {
            if(etPeso.text.toString() != "" && etAltura.text.toString() != "" ) {
                val imc = calcIMC(etPeso.text.toString(), etAltura.text.toString())
                val df = DecimalFormat("#.00")
                val imcResp = "IMC: " + df.format(imc) + "\n" + checkIMC(imc)
                tvResp.text = imcResp
            }
            else{
                tvResp.text = "Valores nulos."
            }
            it.hideKeyboard()
        }
    }
    private fun calcIMC(peso: String, altura: String): Double  = peso.toDouble() / (altura.toDouble() * altura.toDouble())
    private fun checkIMC(db: Double): String{
        return when(db) {
            in 0.0..17.0 -> "Muito abaixo do peso."
            in 17.1..18.49 -> "Abaixo do peso."
            in 18.5..24.99 -> "Peso normal."
            in 25.0..29.99 ->  "Acima do peso."
            in 30.0..34.99 -> "Obesidade I."
            in 35.0..39.99 -> "Obesidade II(severa)."
            else -> "Obesidade III(mórbida)."
        }
    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
