package com.nazirov.androidimperative.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.nazirov.androidimperative.R
import com.nazirov.androidimperative.databinding.ActivityMainBinding
import com.nazirov.androidimperative.utils.Logger
import com.nazirov.androidimperative.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val viewModel:MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        initObserves()
        binding.btnTest.setOnClickListener {
            viewModel.apiTVShowPopular(2)
        }
        viewModel.apiTVShowPopular(1)

    }

    private fun initObserves() {
        //Retrofit related
        viewModel.tvShowsFromApi.observe(this,{
            Logger.d(TAG, it?.size.toString())
            //refreshadapter
        })
        viewModel.errorMessage.observe(this,{
            Logger.d(TAG,it.toString())
        })
        viewModel.isLoading.observe(this,{
            Logger.d(TAG,it.toString())
            if (it){

            }else{

            }
        })
    }
}