package com.example.youtube.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.App
import com.example.youtube.BuildConfig
import com.example.youtube.core.ui.BaseViewModel
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.data.remote.model.ApiService
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource
import com.example.youtube.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel : BaseViewModel() {



    fun getPlayLists(): LiveData<Resource<Playlist>> {
        return App.repository.getPlayLists() ;
    }

}