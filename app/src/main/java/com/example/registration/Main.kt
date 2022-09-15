package com.example.registration

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val employee = Employee(
        1,
        "Brenda Soledad Rodríguez Cortes",
        AcademicLevel.DOCTORAL,
        "ROCB000819MOCDRRA1",
        LocalDate.parse("2000-09-08"),
        "A012"
    )

    val period = Period(
    LocalDate.parse("2022-09-01"),
    LocalDate.parse("2022-09-15"),
    "Novena quincena del 2022"

    )

    var listDetails = arrayListOf<Schedule.Detail>(
        Schedule.Detail(
            DayOfWeek.MONDAY,
            LocalTime.parse("08:00"),
            LocalTime.parse("16:00")
        ),
        Schedule.Detail(
            DayOfWeek.TUESDAY,
            LocalTime.parse("08:00"),
            LocalTime.parse("16:00")
        ))

    val schedule= Schedule.Builder(employee, period)
        .addMany(listDetails).build()

    val listOfCheckInOut = listOf<CheckInOut>(
        CheckInOut(
            LocalDate.parse("2022-09-12"),
            employee,
            LocalTime.parse("08:00"),
            LocalTime.parse("16:00")
        ),
        CheckInOut(
            LocalDate.parse("2022-09-13"),
            employee,
            LocalTime.parse("09:00"),
            LocalTime.parse("16:00")
        ))

    val incident=Incident(
        employee,
        schedule,
        listOfCheckInOut,
        listOf(),
        LocalDate.parse("2022-09-12"),
        LocalDate.parse("2022-09-13")
    )
    println("Nombre: ${employee.fullName}")
    println("Grado academico: ${employee.academicLevel}")
    println("Curp: ${employee.curp}")
    println("Faltas: ${incident.getAbsences()}")


}
