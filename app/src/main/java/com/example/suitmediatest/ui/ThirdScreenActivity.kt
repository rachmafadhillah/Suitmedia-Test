package com.example.suitmediatest.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatest.adapter.LoadingStateAdapter
import com.example.suitmediatest.adapter.UserAdapter
import com.example.suitmediatest.databinding.ActivityThirdScreenBinding
import com.example.suitmediatest.ui.viewmodel.MainViewModel
import com.example.suitmediatest.ui.viewmodel.ViewModelFactory

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.rvUser.layoutManager = LinearLayoutManager(this)

        getData()
    }

    private fun getData() {
        val adapter = UserAdapter()
        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        mainViewModel.user.observe(this) {
            adapter.submitData(lifecycle, it)
        }

        adapter.setOnItemClickListener { user ->
            val resultIntent = Intent().apply {
                putExtra("SELECTED_USER_NAME", "${user.firstName} ${user.lastName}")
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
