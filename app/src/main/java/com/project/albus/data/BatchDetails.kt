package com.project.albus.data

data class BatchDetails(
        var owner:String,
        var code:String,
        var members: MutableList<String?>? = mutableListOf(),
        var schedules: MutableList<ScheduleData>?= mutableListOf(),
        var name: String,
)
{
   constructor() : this(""," ", mutableListOf(),
            mutableListOf(), "")
    }