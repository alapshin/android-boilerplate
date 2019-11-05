package com.alapshin.boilerplate.posts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alapshin.boilerplate.base.BaseMviFragment
import com.alapshin.boilerplate.base.viewBinding
import com.alapshin.boilerplate.common.recyclerview.OnItemClickListener
import com.alapshin.boilerplate.databinding.PostListFragmentBinding
import com.alapshin.boilerplate.log.LogUtil
import com.alapshin.boilerplate.posts.data.Post
import com.alapshin.boilerplate.posts.presentation.PostListViewModel
import com.alapshin.boilerplate.posts.widget.PostAdapter

class PostListFragment : BaseMviFragment<PostListViewModel.State>() {
    private val adapter = PostAdapter()
    private val binding by viewBinding {
        PostListFragmentBinding.inflate(layoutInflater)
    }
    private val postViewModel: PostListViewModel by viewModels { vmFactory.create(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            postViewModel.dispatch(PostListViewModel.Event.Idle())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.postsListRecyclerview.adapter = adapter
        adapter.onItemClickListener = object : OnItemClickListener<Post> {
            override fun onItemClick(item: Post?, position: Int) {
                item?.let {
                    findNavController().navigate(
                        PostListFragmentDirections.actionDetail(item.id)
                    )
                }
            }
        }
        binding.postsListSwipeLayout.setOnRefreshListener {
            postViewModel.dispatch(PostListViewModel.Event.Refresh())
        }

        postViewModel.state.observe(viewLifecycleOwner, Observer<PostListViewModel.State> {
            render(it)
        })
    }

    override fun render(state: PostListViewModel.State) {
        LogUtil.d("State " + state.progress + " " + state.posts?.size)
        if (state.progress) {
            val initial = state.posts?.isEmpty() == true
            val refresh = binding.postsListSwipeLayout.isRefreshing
            if (initial && !refresh) {
                binding.postsListProgressBar.show()
            }
        } else if (state.posts != null) {
            adapter.submitList(state.posts)
            binding.postsListEmptyMessage.isVisible = state.posts.isEmpty()
            binding.postsListProgressBar.hide()
            binding.postsListSwipeLayout.isRefreshing = false
        }
    }
}
