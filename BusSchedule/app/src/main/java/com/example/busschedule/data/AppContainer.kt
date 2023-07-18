package com.example.busschedule.data

import android.content.Context

interface AppContainer{
    val scheduleRepository: BusSchedulesRepository
}

class AppDataContainer(private val context: Context): AppContainer{
    override val scheduleRepository: BusSchedulesRepository by lazy {
        OfflineScheduleRepository(BusScheduleDatabase.getDatabase(context).BusScheduleDao())
    }
}