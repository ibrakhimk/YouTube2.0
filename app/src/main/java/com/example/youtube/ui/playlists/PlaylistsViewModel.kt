package com.example.youtube.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.BuildConfig
import com.example.youtube.base.BaseViewModel
import com.example.youtube.model.Playlist
import com.example.youtube.remote.ApiService
import com.example.youtube.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlist(): LiveData<Playlist> {
        return getPlayLists();
    }

    private fun getPlayLists(): LiveData<Playlist> {

        val data = MutableLiveData<Playlist>()

        RetrofitClient.create()
            .getPlaylists(BuildConfig.API_KEY, "contentDetails,snippet", "UCWOA1ZGywLbqmigxE4Qlvuw",30)
            .enqueue(object : Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    print(t.stackTrace)
                    //404 - not found, 403 - not connection, 401 - токен истек
                }
            })
        return data
    }
}