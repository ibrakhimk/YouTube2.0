package com.example.youtube.ui.playlists.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.youtube.databinding.ItemListBinding
import com.example.youtube.utils.loadImage
import com.example.youtube.data.remote.model.Playlist

class PlaylistsAdapter(private val onClick: (Playlist.Item) -> Unit) :
    Adapter<PlaylistsAdapter.PlaylistsViewHolder>() {

    private var list = ArrayList<Playlist.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<Playlist.Item?>?) {
        this.list = list as ArrayList<Playlist.Item>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size;

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class PlaylistsViewHolder(private val binding: ItemListBinding) :
        ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(playlist: Playlist.Item) {
            if (playlist.snippet?.title.isNullOrBlank()) {
                binding.nameList.text = "null"
            } else {
                binding.nameList.text = playlist.snippet?.title
            }
            binding.descList.text = playlist.contentDetails?.itemCount.toString() + " video series"
            binding.imgList.loadImage(playlist.snippet?.thumbnails?.default?.url!!)
            binding.cardView.setOnClickListener {
                onClick.invoke(playlist)
            }
        }
    }
}