package com.example.generalmeeting.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.generalmeeting.R
import com.example.generalmeeting.viewModel.ResultViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.member_row.view.*

class ResultAdapter(
    private val resultViewModel: ResultViewModel
) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View?)
        : RecyclerView.ViewHolder(containerView!!), LayoutContainer {
        init {
            containerView?.apply {
                editText.isEnabled = false
                checkBox.isEnabled = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.member_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return resultViewModel.members.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val member = resultViewModel.members[position]
        holder.containerView!!.apply {
            if (position > 0) {
                val previousMember = resultViewModel.members[position-1]
                if (previousMember.group != member.group) {
                    val layout = layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams = layout.apply {
                        topMargin = 16
                    }
                }
            }
            editText.setText(member.name)
            checkBox.isChecked = member.enable
        }
    }
}