package com.example.busschedule.data

import android.content.ClipData.Item
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    @Insert
    suspend fun insert(schedule: BusSchedule)

    @Update
    suspend fun update(schedule: BusSchedule)

    @Delete
    suspend fun delete(schedule: BusSchedule)

    @Query("SELECT * FROM Schedule WHERE id = :id")
    fun getSchedule(id: Int): Flow<BusSchedule>

    @Query("SELECT * FROM Schedule ORDER BY stop_name ASC")
    fun getAllSchedules(): Flow<List<BusSchedule>>

    @Query("SELECT * FROM Schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC")
    fun getByStopName(stopName: String): Flow<List<BusSchedule>>
}