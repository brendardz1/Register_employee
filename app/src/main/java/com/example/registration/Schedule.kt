package com.example.registration

import java.time.DayOfWeek
import java.time.LocalTime

class Schedule private  constructor (private val details: ArrayList<Detail>) {

    data class Builder(
        val employee: Employee,
        val period: Period
    ) {

        private val details = ArrayList<Detail>()

        fun addOne(scheduleDetail: Detail): Builder {
            details.add(scheduleDetail)
            return this
        }

        fun addMany(scheduleDetails: ArrayList<Detail>): Builder {
            details.addAll(scheduleDetails)
            return this
        }

        fun build() = Schedule(details)
    }

    class Detail(
        val dayOfWeek: DayOfWeek,
        val checkIn: LocalTime,
        val checkOut: LocalTime
    )
    fun getDetails() = details
}

