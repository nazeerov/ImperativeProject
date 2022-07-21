package com.nazirov.androidimperative.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nazirov.androidimperative.R
import com.nazirov.androidimperative.adapter.TVShowAdapter
import com.nazirov.androidimperative.databinding.ActivityMainBinding
import com.nazirov.androidimperative.model.TVShow
import com.nazirov.androidimperative.utils.Logger
import com.nazirov.androidimperative.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: TVShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        initObserves()
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.rvHome.layoutManager = gridLayoutManager
        refreshAdapter(ArrayList())

        binding.rvHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == viewModel.tvShowsFromApi.value!!.size - 1) {
                    val nextPage = viewModel.tvShowPopular.value!!.page + 1
                    viewModel.apiTVShowPopular(nextPage)
                }
            }
        })
        binding.bFab.setOnClickListener {
            binding.rvHome.smoothScrollToPosition(0)
        }

        viewModel.apiTVShowPopular(1)
    }

    private fun initObserves() {
        // Retrofit Related
        viewModel.tvShowsFromApi.observe(this, {
            Logger.d(TAG, it!!.size.toString())
            adapter.setNewTVShows(it!!)
        })

        viewModel.tvShowDetails.observe(this, {
            Logger.d(TAG, it.toString())
        })

        viewModel.errorMessage.observe(this, {
            Logger.d(TAG, it.toString())
        })

        /*viewModel.isLoading.observe(this, Observer {
            Logger.d(TAG, it.toString())
            if (viewModel.isLoading.value == true){
                binding.pbLoading.visibility = View.VISIBLE
            }else{
                binding.pbLoading.visibility = View.GONE
            }
        })

        // Room Related
        viewModel.tvShowsFromDB.observe(this, {
            Logger.d(TAG, it!!.size.toString())
        })*/
    }

    private fun refreshAdapter(items: ArrayList<TVShow>) {
        adapter = TVShowAdapter(this, items)
        binding.rvHome.adapter = adapter
    }

}