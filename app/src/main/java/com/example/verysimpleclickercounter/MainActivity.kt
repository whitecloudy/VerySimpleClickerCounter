package com.example.verysimpleclickercounter

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.example.verysimpleclickercounter.databinding.ActivityMainBinding


class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding
    var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.numberView.text = counter.toString()
        binding.numberView.setOnLongClickListener{resetCounter()}
        binding.numberView.setOnClickListener{countOne()}

        //setContentView(R.layout.activity_main)
        //textNumber.setText("what?")

    }

    private fun resetCounter(): Boolean {
        counter = 0
        binding.numberView.text = counter.toString()
        return true
    }

    private fun countOne(): Boolean {
        counter++
        if (counter >= 1000)
            resetCounter()
        binding.numberView.text = counter.toString()
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event != null) {
            if (event.repeatCount == 0) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Log.d("button", "$keyCode is pushed")
                    countOne()
                    return true
                } else
                    return super.onKeyDown(keyCode, event)
            } else
                return super.onKeyDown(keyCode, event)
        } else
            return super.onKeyDown(keyCode, event)
    }
}