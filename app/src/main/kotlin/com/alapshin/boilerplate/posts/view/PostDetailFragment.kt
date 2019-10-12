package com.alapshin.boilerplate.posts.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.alapshin.boilerplate.BuildConfig
import com.alapshin.boilerplate.base.BaseMviFragment
import com.alapshin.boilerplate.base.viewBinding
import com.alapshin.boilerplate.databinding.PostsDetailFragmentBinding
import com.alapshin.boilerplate.log.LogUtil
import com.alapshin.boilerplate.posts.presentation.PostDetailViewModel
import com.bumptech.glide.Glide

class PostDetailFragment : BaseMviFragment<PostsDetailFragmentBinding, PostDetailViewModel.State>() {
    val args: PostDetailFragmentArgs by navArgs()
    override val binding by viewBinding {
        PostsDetailFragmentBinding.inflate(layoutInflater)
    }
    val postViewModel: PostDetailViewModel by viewModels { vmFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postViewModel.dispatch(PostDetailViewModel.Event.Get(args.postId))
        postViewModel.state.observe(viewLifecycleOwner, Observer {
            render(it)
        } )
    }

    override fun render(state: PostDetailViewModel.State) {
        LogUtil.d(state.toString())
        if (state.post != null) {
            binding.postsDetailBody.text = state.post.body
            binding.postsDetailTitle.text =  state.post.title
            Glide.with(this)
                .load(String.format(BuildConfig.IMAGE_URL, state.post.id))
                .into(binding.postsDetailImage);
        }
    }
}
