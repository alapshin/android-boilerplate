package com.alapshin.boilerplate.posts.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alapshin.boilerplate.base.BaseMviFragment
import com.alapshin.boilerplate.base.viewBinding
import com.alapshin.boilerplate.common.recyclerview.BaseAdapter
import com.alapshin.boilerplate.databinding.PostsListFragmentBinding
import com.alapshin.boilerplate.log.LogUtil
import com.alapshin.boilerplate.posts.data.Post
import com.alapshin.boilerplate.posts.presentation.PostListViewModel
import com.alapshin.boilerplate.posts.widget.PostAdapter

class PostListFragment : BaseMviFragment<PostsListFragmentBinding, PostListViewModel.State>() {
    override val binding by viewBinding {
        PostsListFragmentBinding.inflate(layoutInflater)
    }
    private val postViewModel: PostListViewModel by viewModels { vmFactory }

    val adapter = PostAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isRestored()) {
            postViewModel.dispatch(PostListViewModel.Event.Idle())
        }
        postViewModel.state.observe(viewLifecycleOwner, Observer<PostListViewModel.State> {
            render(it)
        })

        binding.postsListRecyclerview.adapter = adapter
        adapter.onItemClickListener = object : BaseAdapter.OnItemClickListener<Post> {
            override fun onItemClick(item: Post, position: Int) {
                findNavController().navigate(
                    PostListFragmentDirections.actionDetail(item.id)
                )
            }
        }
    }

    override fun render(state: PostListViewModel.State) {
        LogUtil.d(state.toString())
        if (state.progress) {
            binding.postsListProgressBar.show()
        } else {
            binding.postsListProgressBar.hide()
        }
        if (state.posts?.isNotEmpty() == true) {
            adapter.submitList(state.posts)
        }
    }
}
