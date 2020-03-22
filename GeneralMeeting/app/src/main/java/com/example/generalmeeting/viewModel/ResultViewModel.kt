package com.example.generalmeeting.viewModel

import androidx.lifecycle.ViewModel
import com.example.generalmeeting.models.Member

class ResultViewModel : ViewModel() {

    val members: MutableList<Member> = mutableListOf()

    fun removeAt(index: Int) {
        members.removeAt(index)
    }

    fun swap(fromPosition: Int, toPosition: Int) {
        val temp = members[toPosition]
        members[toPosition] = members[fromPosition]
        members[fromPosition] = temp
    }

    /**
     *  Memberをグループ分けする
     */
    fun setGroup(max: Int) {
        val groupTypes = (1..max).map { it }
        members.apply {
            shuffle()
            forEachIndexed { index: Int, member: Member ->
                val groupTypesIndex = index % groupTypes.size
                member.group = groupTypes[groupTypesIndex]
            }
            sortBy { it.group }
        }
    }

}