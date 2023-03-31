package org.d3if0074.healthyfur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.d3if0074.healthyfur.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}