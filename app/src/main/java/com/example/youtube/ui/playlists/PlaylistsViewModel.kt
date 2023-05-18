package com.example.youtube.ui.playlists

import androidx.lifecycle.LiveData
import com.example.youtube.App
import com.example.youtube.core.ui.BaseViewModel
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.core.network.result.Resource

class PlaylistsViewModel : BaseViewModel() {

    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return App().repository.getPlaylists()
    }
}