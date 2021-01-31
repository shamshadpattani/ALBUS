package com.project.albus.data

data class BatchDetails (
    var name: String,
    var code:String,
    var owner:String,
    var members:List<String>?,
    var schedules: List<ScheduleData>?
)
{
   constructor() : this(""," ","",
            emptyList(), emptyList())
    }