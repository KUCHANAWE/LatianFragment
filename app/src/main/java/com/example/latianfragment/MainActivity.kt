package com.example.latianfragment

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.frameContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mFragmentManager = supportFragmentManager
        val mfSatu = Halaman1()

        mFragmentManager.findFragmentByTag(Halaman1::class.java.simpleName)
        mFragmentManager.beginTransaction()
            .add(R.id.frameContainer, mfSatu, Halaman1::class.java.simpleName).commit()

        val button1 = findViewById<Button>(R.id.halaman1)
        val button2 = findViewById<Button>(R.id.halaman2)
        val button3 = findViewById<Button>(R.id.halaman3)

        button1.setOnClickListener {
            val mFragmentManager1 = supportFragmentManager
            val mfSatu = Halaman1()

            mFragmentManager1.findFragmentByTag(Halaman1::class.java.simpleName)
            mFragmentManager1.beginTransaction().apply {
                replace(R.id.frameContainer, mfSatu, Halaman1::class.java.simpleName).commit()
            }
        }

        button2.setOnClickListener {
            val mFragmentManager2 = supportFragmentManager
            val mfDua = Halaman2()

            mFragmentManager2.findFragmentByTag(Halaman2::class.java.simpleName)
            mFragmentManager2.beginTransaction().apply {
                replace(R.id.frameContainer, mfDua, Halaman2::class.java.simpleName).commit()
            }
        }

        button3.setOnClickListener {
            val mFragmentManager3 = supportFragmentManager
            val mfTiga = Halaman3()

            mFragmentManager3.findFragmentByTag(Halaman3::class.java.simpleName)
            mFragmentManager3.beginTransaction().apply {
                replace(R.id.frameContainer, mfTiga, Halaman3::class.java.simpleName).commit()
            }
        }
    }
}