package ru.sample.store.binscaner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.sample.store.binscaner.databinding.ActivityMainBinding
import ru.sample.store.binscaner.view.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerMain, MainFragment.newInstance())
                .commit()
        }
    }
}