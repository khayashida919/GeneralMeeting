package com.example.generalmeeting.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.generalmeeting.R
import com.example.generalmeeting.utility.hideKeyboard
import com.example.generalmeeting.utility.showToast
import com.example.generalmeeting.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val mainAdapter: MainAdapter by lazy { MainAdapter(mainViewModel) }

    private val itemTouchHelper = ItemTouchHelper(
        object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder): Boolean {
            mainViewModel.swap(viewHolder.adapterPosition, target.adapterPosition)
            mainAdapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            mainViewModel.removeAt(viewHolder.adapterPosition)
            mainAdapter.notifyItemRemoved(viewHolder.adapterPosition)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        mainAdapter.notifyDataSetChanged()
        itemTouchHelper.attachToRecyclerView(recyclerView)

        shuffleFab.setOnClickListener {
            hideKeyboard()
1
            val count = groupCountEditText.text.toString().toIntOrNull() ?: run {
                showToast("グループ数を入力してください")
                return@setOnClickListener
            }
            if (count == 0) {
                showToast("グループ数を入力してください")
                return@setOnClickListener
            }

            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra(MEMBERS_DATA, mainViewModel.members.toTypedArray())
                putExtra(GROUP_COUNT, count)
            }
            startActivity(intent)
        }

        addFab.setOnClickListener {
            mainViewModel.add()
            mainAdapter.notifyItemInserted(0)
        }

        hideKeyboard()
    }

    companion object {
        const val MEMBERS_DATA = "MEMBERS_DATA"
        const val GROUP_COUNT = "GROUP_COUNT"
    }
}
