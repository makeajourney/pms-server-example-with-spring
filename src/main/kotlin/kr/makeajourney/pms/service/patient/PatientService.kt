package kr.makeajourney.pms.service.patient


import kr.makeajourney.pms.domain.hospital.Hospital
import kr.makeajourney.pms.domain.patient.Patient
import kr.makeajourney.pms.domain.patient.PatientRepository
import kr.makeajourney.pms.domain.patient.QPatientRepository
import kr.makeajourney.pms.web.dto.PatientSaveRequestDto
import kr.makeajourney.pms.web.dto.PatientUpdateRequestDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class PatientService(
    val patientRepository: PatientRepository,
    val qPatientRepository: QPatientRepository,
) {

    fun save(hospital: Hospital, requestDto: PatientSaveRequestDto): Long {
        return patientRepository.save(requestDto.toEntity(hospital)).id
    }

    @Transactional
    fun update(patient: Patient, requestDto: PatientUpdateRequestDto): Long {
        patient.update(
            requestDto.name,
            requestDto.registrationNo,
            requestDto.sex,
            requestDto.birthDate.toString(),
            requestDto.phoneNumber
        )
        return patient.id
    }

    fun findById(patientId: Long): Patient? {
        return patientRepository.findByIdOrNull(patientId)
    }

    @Transactional
    fun delete(patient: Patient) {
        patientRepository.delete(patient)
    }

    fun findByHospitalIdAndQuery(
        hospitalId: Long,
        name: String?,
        registrationNo: String?,
        birthDate: String?,
        pageable: Pageable,
    ): Page<Patient> {
        return qPatientRepository.findByHospitalIdAndQuery(
            hospitalId,
            name,
            registrationNo,
            birthDate,
            pageable
        )
    }
}
