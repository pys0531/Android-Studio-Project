package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository(private val busScheduleDao: BusScheduleDao): BusSchedulesRepository{
    override fun getAllScheduleStream(): Flow<List<BusSchedule>> = busScheduleDao.getAllSchedules()

    override fun getScheduleStream(id: Int): Flow<BusSchedule?> = busScheduleDao.getSchedule(id)

    override fun getByStopName(stopName: String): Flow<List<BusSchedule>> = busScheduleDao.getAllSchedules()

    override suspend fun insertSchedule(schedule: BusSchedule) = busScheduleDao.insert(schedule)

    override suspend fun updateSchedule(schedule: BusSchedule) = busScheduleDao.update(schedule)

    override suspend fun deleteSchedule(schedule: BusSchedule) = busScheduleDao.delete(schedule)
}