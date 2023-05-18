package com.example.youtube.data.remote.model

import com.example.youtube.BuildConfig
import com.example.youtube.core.network.BaseDataSource
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource
import com.example.youtube.data.remote.PlaylistItem

class RemoteDataSourse() : BaseDataSource() {

    suspend fun getPlaylists(): Resource<Playlist> {
        return getResult {
            ApiService.getPlaylists(
                BuildConfig.API_KEY,
                "contentDetails,snippet",
                "UCWOA1ZGywLbqmigxE4Qlvuw",
                30
            )
        }
    }

    suspend fun getPlaylistItems(playlistId: String): Resource<PlaylistItem> {
        return getResult {
            apiService.getPlaylists(
                BuildConfig.API_KEY,
                "contentDetails,snippet",
                playlistId!!,
                30
            )
        }
    }
}