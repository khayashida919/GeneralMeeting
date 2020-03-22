package com.example.generalmeeting.view

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.generalmeeting.R
import com.example.generalmeeting.viewModel.MainViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.member_row.view.*

class MainAdapter(
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    //セルに対して一度しか設定したくないリスナーなどはここで設定
    inner class ViewHolder(override val containerView: View?)
        : RecyclerView.ViewHolder(containerView!!), LayoutContainer {
        init {
            containerView!!.apply {
                editText.addTextChangedListener {
                    mainViewModel.members[adapterPosition].name = it?.toString() ?: ""
                }
                checkBox.setOnClickListener {
                    mainViewModel.members[adapterPosition].enable = (it as? CheckBox)?.isChecked ?: false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.member_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mainViewModel.members.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.containerView!!.apply {
            editText.setText(mainViewModel.members[position].name)
            checkBox.isChecked = mainViewModel.members[position].enable
        }
    }
}