package com.example.youtube.data.remote

import com.example.youtube.BuildConfig
import com.example.youtube.core.network.BaseDataSource
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.data.remote.model.PlaylistItem

class RemoteDataSource : BaseDataSource() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    suspend fun getPlaylists(): Resource<Playlist> {
        return getResult {
            apiService.getPlaylists(
                BuildConfig.API_KEY,
                "contentDetails,snippet",
                "UCWOA1ZGywLbqmigxE4Qlvuw",
                30
            )
        }
    }

    suspend fun getPlaylistItems(playlistId: String): Resource<PlaylistItem> {
        return getResult {
            apiService.getPlaylistItems(
                BuildConfig.API_KEY,
                "contentDetails,snippet",
                playlistId,
                30
            )
        }
    }
}