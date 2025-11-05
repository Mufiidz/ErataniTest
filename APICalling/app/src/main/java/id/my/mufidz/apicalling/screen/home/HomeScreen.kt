package id.my.mufidz.apicalling.screen.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.my.mufidz.apicalling.base.BaseActivity
import id.my.mufidz.apicalling.databinding.ActivityHomeScreenBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreen : BaseActivity<ActivityHomeScreenBinding>(ActivityHomeScreenBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    private val adapter by lazy { UserTableAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.execute(HomeAction.ShowUser)

        lifecycleScope.launch {
            viewModel.viewState.collect {
                when (it) {
                    is HomeState.Error -> errorView(it)
                    HomeState.Initial -> {}
                    HomeState.Loading -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.headerTable.visibility = View.INVISIBLE
                        binding.rvUser.visibility = View.INVISIBLE
                        binding.tvError.visibility = View.GONE
                    }
                    is HomeState.Success -> successView(it)
                }
            }
        }
    }

    private fun errorView(error: HomeState.Error) {
        binding.apply {
            loading.visibility = View.GONE
            headerTable.visibility = View.INVISIBLE
            rvUser.visibility = View.INVISIBLE
            tvError.apply {
                visibility = View.VISIBLE
                text = error.message
            }
        }
    }

    private fun successView(success: HomeState.Success) {
        adapter.setData(success.users.toMutableList())
        binding.apply {
            loading.visibility = View.GONE
            headerTable.visibility = View.VISIBLE
            tvError.visibility = View.GONE
            rvUser.apply {
                visibility = View.VISIBLE
                layoutManager = LinearLayoutManager(this@HomeScreen)
                adapter = this@HomeScreen.adapter
            }
        }

    }
}