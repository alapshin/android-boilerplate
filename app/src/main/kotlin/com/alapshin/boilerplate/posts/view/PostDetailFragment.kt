package com.alapshin.boilerplate.posts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.alapshin.boilerplate.BuildConfig
import com.alapshin.boilerplate.base.BaseMviFragment
import com.alapshin.boilerplate.base.viewBinding
import com.alapshin.boilerplate.databinding.PostDetailFragmentBinding
import com.alapshin.boilerplate.posts.presentation.PostDetailViewModel
import com.bumptech.glide.Glide
import com.happify.mvi.core.bind

class PostDetailFragment : BaseMviFragment<PostDetailViewModel.State>() {
    private val args: PostDetailFragmentArgs by navArgs()
    private val binding by viewBinding {
        PostDetailFragmentBinding.inflate(layoutInflater)
    }
    private val postViewModel: PostDetailViewModel by viewModels { vmFactory.create(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            postViewModel.process(PostDetailViewModel.Event.Get(args.postId))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind(this, postViewModel)
    }

    override fun render(state: PostDetailViewModel.State) {
        if (state.post != null) {
            binding.postDetailBody.text = state.post.body
            binding.postDetailTitle.text = state.post.title
            Glide.with(this)
                .load(String.format(BuildConfig.IMAGE_URL, state.post.id))
                .into(binding.postDetailImage)
        }
    }
}
