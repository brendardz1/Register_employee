package com.example.registration

import java.time.LocalDate

fun hasPermission(employee: Employee, date: LocalDate, permissions: List<Permission>): Boolean {
    val permission = permissions.firstOrNull{it.date==date && it.employee==employee}
    return (permission!=null)
}