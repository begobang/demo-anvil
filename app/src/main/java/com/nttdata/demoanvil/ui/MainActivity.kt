package com.nttdata.demoanvil.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.nttdata.demoanvil.R
import com.nttdata.demoanvil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ((application as DemoAnvilApp).appComponent).viewModelComponent().viewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initObservers()
    }

    private fun initViews(){
        binding.btnGenerate.setOnClickListener {
            showLoader()
            viewModel.nextMovie()
        }
    }

    private fun initObservers() {
        viewModel.movie.observeForever {
            binding.tvTitle.text = it.title
            binding.cType.text = it.type

            binding.tvRelease.text = applicationContext.getString(R.string.release,it.year)

            binding.ivMovie.load(it.url)

            showLoader(show = false)
        }
    }

    private fun showLoader(show: Boolean = true){
        if(show){
            binding.loader.show()
            binding.tvTitle.hide()
            binding.cgTags.hide()
            binding.tvRelease.hide()
            binding.ivMovie.hide()
        } else {
            binding.loader.hide()
            binding.tvTitle.show()
            binding.cgTags.show()
            binding.tvRelease.show()
            binding.ivMovie.show()
        }
    }

    fun View.show(){
        visibility = View.VISIBLE
    }

    fun View.hide(){
        visibility = View.GONE
    }
}