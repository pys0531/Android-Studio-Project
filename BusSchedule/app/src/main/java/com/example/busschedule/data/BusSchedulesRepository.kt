package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface BusSchedulesRepository {
    fun getAllScheduleStream(): Flow<List<BusSchedule>>

    fun getScheduleStream(id: Int): Flow<BusSchedule?>

    fun getByStopName(stopName: String): Flow<List<BusSchedule>>

    suspend fun insertSchedule(schedule: BusSchedule)

    suspend fun updateSchedule(schedule: BusSchedule)

    suspend fun deleteSchedule(schedule: BusSchedule)
}