package com.example.bmi_calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var edad:Int = 25
    var peso:Int = 75
    var altura:Int = 180
    var sexo:String = "hola"
    override fun onCreate(savedInstanceState: Bundle?) {
        var contador = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekAltura.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p1 <= 100){
                    seekAltura.setProgress(100)
                    tvAltura.text = "100"
                    altura = 100
                }else{
                    tvAltura.text = "$p1"
                    altura = p1
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        btnDecrementarEdad.setOnClickListener  (this)
        btnIncrementarEdad.setOnClickListener  (this)
        btnIncrementarPeso.setOnClickListener  (this)
        btnDecrementarPeso.setOnClickListener  (this)
        imBtnFemenino.setOnClickListener       (this)
        imBtnMasculino.setOnClickListener      (this)
        btnCalcular.setOnClickListener         (this)

        tvPeso.text = "$peso"
        tvAltura.text = "$altura"
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btnDecrementarEdad->{
                edad--
                tvEdad.text = "$edad"
            }
            R.id.btnIncrementarEdad->{
                edad++
                tvEdad.text = "$edad"
            }
            R.id.btnDecrementarPeso->{
               peso--
                tvPeso.text = "$peso"
            }
            R.id.btnIncrementarPeso->{
                peso++
                tvPeso.text = "$peso"
            }
            R.id.imBtnFemenino->sexo = "f"
            R.id.imBtnMasculino->sexo = "m"
            R.id.btnCalcular->{
                val imc = calculaIMC(peso, altura)
                val intencion = Intent(this,Result::class.java)
                intencion.putExtra("imc",imc)
                intencion.putExtra("gender",sexo)
                intencion.putExtra("age",edad)
                startActivity(intencion)
            }

        }
    }
    fun calculaIMC(peso:Int, altura:Int):Float{
        val pesoF = peso.toFloat()
        val alturaF = altura.toFloat()/100
        var imc = pesoF/(alturaF*alturaF)

        imc.toFloat()
        return imc.toFloat()
    }
}