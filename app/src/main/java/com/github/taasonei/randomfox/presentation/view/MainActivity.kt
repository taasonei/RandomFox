package com.github.taasonei.randomfox.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.taasonei.randomfox.R
import com.github.taasonei.randomfox.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val icons = resources.obtainTypedArray(R.array.tab_icons)

            tab.text = resources.getStringArray(R.array.tabs_text)[position]
            tab.icon = icons.getDrawable(position)

            icons.recycle()
        }.attach()
    }

}
