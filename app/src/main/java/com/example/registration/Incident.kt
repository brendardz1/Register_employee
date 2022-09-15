package com.example.registration

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.registration.Constants.Companion.MINIMUM_SENIORITY_FREE_RETARDANT
import com.example.registration.Constants.Companion.NUMBER_OF_RETARDANT_EQUIVALENT_TO_ONE_ABSENCE
import java.time.LocalDate


data class Incident (val employee: Employee,
                     val currentSchedule: Schedule,
                     val checksInOut: List<CheckInOut>,
                     val permissions: List<Permission>,
                     val initialDate: LocalDate,
                     val finalDate: LocalDate
){
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getRetardant(): Int {
        val checks= checksInOut.filter { it.employee==employee &&
                it.date>=initialDate && it.date<=finalDate
        }
        return checks.count { it.isRetardant(currentSchedule,permissions) }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getAbsences(): Int {
        val checks= checksInOut.filter { it.employee==employee &&
                it.date>=initialDate && it.date<=finalDate
        }
        val expectedChecks=getExpectedChecks()
        val isApplyRetardant = employee.getSeniority()<MINIMUM_SENIORITY_FREE_RETARDANT
        val retardant =  getRetardant()
        val absencesForRetardant = when(isApplyRetardant){
            true -> (retardant / NUMBER_OF_RETARDANT_EQUIVALENT_TO_ONE_ABSENCE)
            else ->0
        }
        val absenceForIncorrectRegistration = checks.count {
            it.isAbsenceForIncorrectRegistration(currentSchedule,permissions)
        }
        val absences = absenceForIncorrectRegistration + expectedChecks.count {
            checks.firstOrNull{check->check.date==it.date}==null &&
                    !hasPermission(employee,it.date,permissions)
        }

        return (absences + absencesForRetardant )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getExpectedChecks(): List<CheckInOut>{
        val expectedChecks= ArrayList<CheckInOut>()
        var dayOfWeek= initialDate.dayOfWeek
        var expectedDateOfRegistration =initialDate
        val scheduleDetails= currentSchedule.getDetails()
        while (expectedDateOfRegistration<=finalDate ) {
            val dayScheduleDetail = scheduleDetails.firstOrNull { it.dayOfWeek == dayOfWeek }
            if (dayScheduleDetail != null) {
                expectedChecks.add(CheckInOut(expectedDateOfRegistration,
                    employee,dayScheduleDetail.checkIn, dayScheduleDetail.checkOut))
            }
            dayOfWeek = dayOfWeek.plus(1)
            expectedDateOfRegistration = expectedDateOfRegistration.plusDays(1)
        }
        return expectedChecks
    }
}
