package com.example.implicitintents

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.implicitintents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.urlButton.setOnClickListener {
            openWebsite()
        }

        binding.locButton.setOnClickListener {
            openLocation()
        }

        binding.shareButton.setOnClickListener {
            shareText()
        }
    }

    private fun openWebsite() {
        val webIntent = Uri.parse(binding.urlEditText.text.toString()).let {
            Intent(Intent.ACTION_VIEW, it)
        }

        try {
            startActivity(webIntent)
        } catch (e: ActivityNotFoundException) {
           Log.d("TAG", "Can't open this webpage")
        }
    }

    fun openLocation() {
        val mapIntent = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California").let {
            Intent(Intent.ACTION_VIEW, it)
        }

        try {
            startActivity(mapIntent)
        } catch (e: ActivityNotFoundException) {
            Log.d("TAG", "Can't open this location")
        }
    }

    fun shareText() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        try {
            startActivity(shareIntent)
        } catch (e: ActivityNotFoundException) {
            Log.d("TAG", "Can't share this text")
        }
    }
}