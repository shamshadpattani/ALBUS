package com.project.albus.ui.schedule

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.project.albus.R
import com.project.albus.data.BatchDetails
import com.project.albus.data.ScheduleData

class ScheduleItemQuickAdapter(data: MutableList<ScheduleData>?):
    BaseQuickAdapter<ScheduleData, BaseViewHolder>(R.layout.list_batch_details,data as MutableList<ScheduleData>) {

    override fun convert(holder: BaseViewHolder, item: ScheduleData) {
        holder.setText(R.id.subjectDate, item.date)
            .setText(R.id.subjectLocation,item.location)
            .setText(R.id.subjectTitle,item.name)
    }
    fun updateItems(newItems: List<ScheduleData>) {
        data.clear()
        data.addAll(newItems)
        notifyDataSetChanged()
    }
}