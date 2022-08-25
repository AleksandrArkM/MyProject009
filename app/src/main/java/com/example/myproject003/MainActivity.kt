package com.example.myproject003

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import com.example.myproject003.data.Post
import com.example.myproject003.data.impl.PostAdapter
import com.example.myproject003.databinding.ActivityMainBinding
import com.example.myproject003.databinding.PostItemBinding
import com.example.myproject003.viewmodel.*

class MainActivity : AppCompatActivity() {

    val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostAdapter(viewModel ::onLikeClicked, viewModel ::onRepostClicked)
        binding.postsRecyclerView.adapter = adapter
        viewModel.data.observe(this) {posts ->
            adapter.submitList(posts)
        }
    }
}
