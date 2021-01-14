package kr.makeajourney.pms.web.dto


import java.time.LocalDate


data class PatientListResponse(
    val name: String,
    val registrationNumber: String,
    val sex: String,
    val birthDate: String?,
    val phoneNumber: String?,
    val latestVisitDate: LocalDate?
)
