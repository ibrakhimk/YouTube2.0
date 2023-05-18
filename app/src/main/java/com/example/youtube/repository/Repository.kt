package com.example.youtube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.youtube.BuildConfig
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource
import com.example.youtube.data.remote.PlaylistItem
import com.example.youtube.data.remote.model.ApiService
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.data.remote.model.RemoteDataSourse
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }
    private val dataSource: RemoteDataSourse by lazy {
        RemoteDataSourse()
    }


    fun getPlayLists(): LiveData<Resource<Playlist>> {

        val data = MutableLiveData<Resource<Playlist>>()

        data.value = Resource.loading()

        RetrofitClient.create()
            .getPlaylists(BuildConfig.API_KEY, "contentDetails,snippet", "UCWOA1ZGywLbqmigxE4Qlvuw",5)
            .enqueue(object : Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful) {
                        data.value = Resource.success(response.body())
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    data.value = Resource.error(t.message.toString(),null,null)
                    //404 - not found, 403 - not connection, 401 - токен истек
                }
            })
        return data
    }
    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItem>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlaylistItems(playlistId)
            emit(response)
        }
    }
}