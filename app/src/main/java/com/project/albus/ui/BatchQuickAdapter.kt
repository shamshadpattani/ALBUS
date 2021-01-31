package com.project.albus.ui


import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.project.albus.R
import com.project.albus.data.BatchDetails

class BatchQuickAdapter(data: MutableList<BatchDetails>?):
        BaseQuickAdapter<BatchDetails,BaseViewHolder>(R.layout.list_schedule,data as MutableList<BatchDetails>) {

    override fun convert(holder: BaseViewHolder, item: BatchDetails) {
        holder.setText(R.id.subjectDate, item.schedules?.get(0)?.date?:"Nothing scheduled")
            .setText(R.id.subjectPlaceOrMember,"${item.members?.size?:"0"} Members" )
            .setText(R.id.subjectTitle,item.name)
    }

    fun updateItems(newItems: List<BatchDetails>) {
        data.clear()
        data.addAll(newItems)
        notifyDataSetChanged()
    }
}

