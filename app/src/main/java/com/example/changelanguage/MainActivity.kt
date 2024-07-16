package com.example.changelanguage

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        loadLocale(this)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var btnenglish:Button = findViewById(R.id.btnenglish)
        var btnkiswahili:Button = findViewById(R.id.btnspanish)

        btnenglish.setOnClickListener{
            setLocale("en")
            recreate()
        }
        btnkiswahili.setOnClickListener{
            setLocale("ks")
            recreate()
        }
    }
    fun Context.setLocale(language:String){
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config,resources.displayMetrics)
        //save the selected language in shared prefrences

        val sharedPreferences:SharedPreferences = getSharedPreferences("Settings",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("My_Lang",language)
        editor.apply()
    }
    fun loadLocale(context:Context){
        val sharedPreferences:SharedPreferences = context.getSharedPreferences("Settings",Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang","")
        language?.let{context.setLocale(it)}
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return super.onContextItemSelected(item)
    }
}