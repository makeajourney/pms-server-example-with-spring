package kr.makeajourney.pms.web.dto


data class PatientResponseDto(
    val id: Long,
    val name: String,
    val registrationNo: String,
    val sex: String,
    val birthDate: String,
    val phoneNumber: String?,
    val visits: List<VisitResponseDto>,
)
