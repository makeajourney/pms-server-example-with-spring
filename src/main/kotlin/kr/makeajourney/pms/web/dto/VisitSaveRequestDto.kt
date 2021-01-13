package kr.makeajourney.pms.web.dto


import com.fasterxml.jackson.annotation.JsonFormat
import kr.makeajourney.pms.domain.patient.Patient
import kr.makeajourney.pms.domain.visit.Visit
import java.time.LocalDateTime


data class VisitSaveRequestDto(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val receptionDateTime: LocalDateTime,
    val statusCode: String,
) {
    fun toEntity(patient: Patient): Visit {
        return Visit(patient.hospital, patient, receptionDateTime, statusCode)
    }
}
