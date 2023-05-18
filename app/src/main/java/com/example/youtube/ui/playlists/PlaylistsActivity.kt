package com.example.youtube.ui.playlists

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.core.network.result.Status
import com.example.youtube.core.ui.BaseActivity
import com.example.youtube.databinding.ActivityPlaylistsBinding
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.ui.detail.DetailActivity
import com.example.youtube.ui.playlists.adapter.PlaylistsAdapter
import com.example.youtube.utils.ConnectionLiveData

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>() {

    private lateinit var adapter: PlaylistsAdapter
    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun initViews() {
        super.initViews()
        adapter = PlaylistsAdapter(this::onClick)
        binding.recyclerView.adapter = adapter
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getPlaylists().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                    adapter.addList(it.data?.items as List<Playlist.Item>)
                    binding.progressBar.isVisible = false
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
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
        ConnectionLiveData(application).observe(this) {
            if (it) {
                binding.internetConnection.visibility = View.VISIBLE
                binding.noConnection.visibility = View.GONE
            } else {
                binding.internetConnection.visibility = View.GONE
                binding.noConnection.visibility = View.VISIBLE
                initViewModel()
            }
        }
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    private fun onClick(item: Playlist.Item) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DESCRIPTION, item.snippet?.description)
        intent.putExtra(TITLE, item.snippet?.title)
        intent.putExtra(ID, item.id)
        startActivity(intent)
    }

    companion object {
        const val ID = "ID"
        const val DESCRIPTION = "DESCRIPTION"
        const val TITLE = "TITLE"
    }

}
