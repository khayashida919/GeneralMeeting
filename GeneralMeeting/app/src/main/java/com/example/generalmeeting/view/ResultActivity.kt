package com.example.generalmeeting.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.generalmeeting.R
import com.example.generalmeeting.models.Member
import com.example.generalmeeting.viewModel.ResultViewModel
import kotlinx.android.synthetic.main.activity_main.*

class ResultActivity : AppCompatActivity() {

    private val resultViewModel: ResultViewModel by viewModels()
    private val resultAdapter: ResultAdapter by lazy { ResultAdapter(resultViewModel) }

    private val itemTouchHelper = ItemTouchHelper(
        object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                resultViewModel.swap(viewHolder.adapterPosition, target.adapterPosition)
                resultAdapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                resultViewModel.removeAt(viewHolder.adapterPosition)
                resultAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setSupportActionBar(toolbar)

        val count = intent.getIntExtra(MainActivity.GROUP_COUNT, 0)
        val members = intent.getSerializableExtra(MainActivity.MEMBERS_DATA) as? Array<Member> ?: return
        resultViewModel.members.addAll(members)
        resultViewModel.setGroup(count)

        recyclerView.apply {
            adapter = resultAdapter
            layoutManager = LinearLayoutManager(this@ResultActivity)
        }
        resultAdapter.notifyDataSetChanged()
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
