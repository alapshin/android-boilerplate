package com.alapshin.boilerplate.posts.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.alapshin.boilerplate.BuildConfig
import com.alapshin.boilerplate.base.HasViewBinding
import com.alapshin.boilerplate.databinding.PostListItemViewBinding
import com.alapshin.boilerplate.posts.data.Post
import com.bumptech.glide.Glide

class PostItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr), HasViewBinding<PostListItemViewBinding> {

    override val binding = PostListItemViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setItem(item: Post) {
        binding.postsListItemBody.text = item.body
        binding.postsListItemTitle.text = item.title
        Glide.with(this)
            .load(String.format(BuildConfig.IMAGE_URL, item.id))
            .into(binding.postsListItemImage)
    }
}
