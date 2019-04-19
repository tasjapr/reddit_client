package com.example.redditclient.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditclient.NetworkManager
import com.example.redditclient.R
import com.example.redditclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RVAdapter.OnItemClickListener, LifecycleOwner {

    lateinit var binding: ActivityMainBinding

    private val rvAdapter = RVAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.entriesRv.layoutManager = LinearLayoutManager(this)
        binding.entriesRv.adapter = rvAdapter

        viewModel.entries.observe(this,
            Observer<ArrayList<Entry>> { it?.let { rvAdapter.replaceData(it) } })

    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}