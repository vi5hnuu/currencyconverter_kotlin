package com.vi5hnu.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var fromCur:String?

    init {
        fromCur="unknown"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fromCurrencyTf=findViewById<EditText>(R.id.fromCurrencytf)
        val toCurrencyTv=findViewById<TextView>(R.id.toCurrencytv)
        val btnConvert=findViewById<Button>(R.id.btnConvert)
        val allCurrency = resources.getStringArray(R.array.currency)

        // access the spinner
        val fromCurrencySpinner = findViewById<Spinner>(R.id.fromCurrency)
        //////////////////////////////////////////////////////////////////////
        btnConvert.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                val multiplier:Double= when{
                    fromCur==allCurrency[0].toString() -> 1.0
                    fromCur==allCurrency[1].toString() -> 0.94
                    fromCur==allCurrency[2].toString() -> 80.92
                    fromCur==allCurrency[3].toString() -> 4.18
                    fromCur==allCurrency[4].toString() -> 3.29
                    else -> 0.0
                }

                val inpVal=fromCurrencyTf.text.toString().toDoubleOrNull()
                toCurrencyTv.setText("₹ ${(inpVal ?: 0.0)*multiplier}")
            }
        })
        //////////////////////////////////////////////////////////////////////
        if (fromCurrencySpinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, allCurrency)
            fromCurrencySpinner.adapter = adapter

            fromCurrencySpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,view: View, position: Int, id: Long) {
                    (parent.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.white))
                    fromCur=allCurrency[position]
                    Toast.makeText(this@MainActivity,
                         "You selected " + allCurrency[position] + " Currency.", Toast.LENGTH_SHORT).show()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }


}