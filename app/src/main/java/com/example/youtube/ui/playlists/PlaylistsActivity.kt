package com.example.youtube.ui.playlists

import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.CheckNetworkConnection
import com.example.youtube.base.BaseActivity
import com.example.youtube.base.BaseViewModel
import com.example.youtube.databinding.ActivityPlaylistsBinding
import com.example.youtube.model.Playlist
import com.example.youtube.ui.playlists.adapter.PlaylistsAdapter

class PlaylistsActivity() :
    BaseActivity<ActivityPlaylistsBinding, BaseViewModel>() {


    private lateinit var adapter: PlaylistsAdapter

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this).get(PlaylistsViewModel::class.java)
    }

    override fun initViews() {
        super.initViews()
        adapter = PlaylistsAdapter(this::onClick)
        binding.recyclerView.adapter = adapter
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.playlist().observe(this) {

            binding.recyclerView.adapter = adapter
            adapter.addList(it.items)
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
        Toast.makeText(this, "ololo", Toast.LENGTH_SHORT).show()
    }
}