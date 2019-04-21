package com.example.redditclient.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditclient.R
import com.example.redditclient.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), RVAdapter.OnItemClickListener, LifecycleOwner {

    lateinit var binding: ActivityMainBinding

    private val rvAdapter = RVAdapter(arrayListOf(), this)
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.entriesRv.layoutManager = LinearLayoutManager(this)
        binding.entriesRv.adapter = rvAdapter

        viewModel.entries.observe(this,
            Observer<ArrayList<Entry>> { it?.let(rvAdapter::replaceData) })

        viewModel.loadTopEntries()
    }

    override fun onItemClick(position: Int) {
        viewModel.openEntryInChromeTab(position, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh ->
                viewModel.loadTopEntries()

            R.id.action_favorites -> Log.d("Bazinga", "")
            //todo
        }

        return true
    }
}
