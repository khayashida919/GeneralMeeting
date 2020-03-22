package com.example.generalmeeting.viewModel

import androidx.lifecycle.ViewModel
import com.example.generalmeeting.models.Member

class MainViewModel : ViewModel() {

    val members: MutableList<Member> = mutableListOf()

    fun add() {
        members.add(
            0, Member("", true)
        )
    }

    fun removeAt(index: Int) {
        members.removeAt(index)
    }

    fun swap(fromPosition: Int, toPosition: Int) {
        val temp = members[toPosition]
        members[toPosition] = members[fromPosition]
        members[fromPosition] = temp
    }

}