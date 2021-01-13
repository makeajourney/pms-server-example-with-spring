package kr.makeajourney.pms.web.dto

import java.time.LocalDate

data class PatientUpdateRequestDto(
    val name: String,
    val registrationNo: String,
    val sex: String,
    val birthDate: LocalDate,
    val phoneNumber: String
)
