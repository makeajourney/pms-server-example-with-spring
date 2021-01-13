package kr.makeajourney.pms.web.dto

import kr.makeajourney.pms.domain.hospital.Hospital
import kr.makeajourney.pms.domain.patient.Patient
import kr.makeajourney.pms.domain.patient.Sex
import java.time.LocalDate

data class PatientSaveRequestDto(
    val name: String,
    val registrationNo: String,
    val sex: String,
    val birthDate: LocalDate,
    val phoneNumber: String
) {
    fun toEntity(hospital: Hospital): Patient {
        return Patient(hospital, name, registrationNo, Sex.code(sex), birthDate.toString(), phoneNumber)
    }
}
