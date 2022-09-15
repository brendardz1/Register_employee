package com.example.registration

import java.time.LocalDate

data class Permission(val employee: Employee,
                      val date: LocalDate,
                      val justification: String,)
