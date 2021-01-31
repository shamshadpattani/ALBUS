package com.project.albus.data

data class BatchDetails (
    var name: String,
    var code:String,
    var owner:String,
    var members:MutableList<String> = mutableListOf(),
    var schedules: MutableList<ScheduleData> = mutableListOf()
)
{
   constructor() : this(""," ","",
            mutableListOf(), mutableListOf())
    }