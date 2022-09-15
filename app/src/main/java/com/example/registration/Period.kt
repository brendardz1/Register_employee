package com.example.registration

import java.time.LocalDate

data class Period(val initialDate: LocalDate,
                  val finalDate: LocalDate,
                  val description: String
)
