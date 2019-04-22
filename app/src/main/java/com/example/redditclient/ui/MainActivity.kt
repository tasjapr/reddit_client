package com.example.redditclient.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditclient.R
import com.example.redditclient.databinding.ActivityMainBinding
import com.example.redditclient.redditAPI.EntriesResponse

class MainActivity : AppCompatActivity(), RVAdapter.OnItemClickListener, LifecycleOwner {

    lateinit var binding: ActivityMainBinding
    var actionMenu: Menu? = null

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
            Observer<ArrayList<EntriesResponse.Data>> { it?.let(rvAdapter::replaceData) })

        viewModel.loadTopEntries()
    }

    override fun onItemClick(position: Int) {
        viewModel.openEntryInChromeTab(position, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        actionMenu = menu
        return true
    }

    var pageNumber = 1
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> viewModel.loadTopEntries()

            R.id.action_next_page -> {
                pageNumber += 1
                viewModel.loadNextPage()
            }

            R.id.action_prev_page -> {
                viewModel.loadPrevPage()
                pageNumber -= 1
            }
        }
        actionMenu!![0].isVisible = pageNumber >= 2

        return true
    }
}
