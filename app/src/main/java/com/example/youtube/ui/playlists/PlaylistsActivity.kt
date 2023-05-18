package com.example.youtube.ui.playlists

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.core.network.result.Status
import com.example.youtube.utils.CheckNetworkConnection
import com.example.youtube.core.ui.BaseActivity
import com.example.youtube.core.ui.BaseViewModel
import com.example.youtube.data.remote.local.Prefs
import com.example.youtube.databinding.ActivityPlaylistsBinding
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.utils.toast
import com.example.youtube.ui.playlists.adapter.PlaylistsAdapter

class PlaylistsActivity() :
    BaseActivity<ActivityPlaylistsBinding, BaseViewModel>() {


    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val text =result.data?.getStringExtra("text")
            }
        }

    private lateinit var adapter: PlaylistsAdapter

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this).get(PlaylistsViewModel::class.java)
    }

    override fun initViews() {
        super.initViews()
        adapter = PlaylistsAdapter(this::onClick)
        binding.recyclerView.adapter = adapter
        Prefs(this).onBoard = true
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getPlayLists().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                    adapter.addList(it.data?.items)
                    binding.progressBar.isVisible = false

                }

                Status.ERROR -> {
                    toast(this, "ololo")
                    binding.progressBar.isVisible = false

                }

                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }

        }
    }

    override fun isConnection() {
        super.isConnection()
        CheckNetworkConnection(application).observe(this) {
            if (it) {
                binding.internetConnection.visibility = View.VISIBLE
                binding.includeNoInternet.root.isVisible = false
            } else {
                binding.internetConnection.visibility = View.GONE
                binding.includeNoInternet.root.isVisible = true
                initViewModel()
            }
        }
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    private fun onClick(item: Playlist.Item) {
    }

}