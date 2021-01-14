package kr.makeajourney.pms.web


import kr.makeajourney.pms.domain.PageRequest
import kr.makeajourney.pms.domain.patient.Patient
import kr.makeajourney.pms.service.hospital.HospitalService
import kr.makeajourney.pms.service.patient.PatientService
import kr.makeajourney.pms.web.dto.PatientListResponse
import kr.makeajourney.pms.web.dto.PatientResponseDto
import kr.makeajourney.pms.web.dto.PatientSaveRequestDto
import kr.makeajourney.pms.web.dto.PatientUpdateRequestDto
import kr.makeajourney.pms.web.dto.VisitResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class PatientController(
    val patientService: PatientService,
    val hospitalService: HospitalService,
) {

    @PostMapping("/api/hospital/{hospitalId}/patient")
    fun save(@PathVariable hospitalId: Long, @RequestBody requestDto: PatientSaveRequestDto): ResponseEntity<Long> {

        val hospital = hospitalService.findById(hospitalId)
            ?: return ResponseEntity.badRequest().build()

        return ResponseEntity.ok(patientService.save(hospital, requestDto))
    }

    @PutMapping("/api/hospital/{hospitalId}/patient/{patientId}")
    fun update(
        @PathVariable hospitalId: Long,
        @PathVariable patientId: Long,
        @RequestBody requestDto: PatientUpdateRequestDto
    ): ResponseEntity<Long> {

        val patient = patientService.findById(patientId)
            ?: return ResponseEntity.noContent().build()

        return if (patient.hospital.id == hospitalId) {
            ResponseEntity.ok(patientService.update(patient, requestDto))
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/api/hospital/{hospitalId}/patient")
    fun getList(
        @PathVariable hospitalId: Long,
        @RequestParam name: String?,
        @RequestParam registrationNo: String?,
        @RequestParam birthDate: String?,
        pageRequest: PageRequest,
    ): ResponseEntity<Page<PatientListResponse>> {

        val pagedPatientList =
            patientService.findByHospitalIdAndQuery(hospitalId, name, registrationNo, birthDate, pageRequest.of())

        val patientList = pagedPatientList.toList()

        if (patientList.isEmpty()) {
            return ResponseEntity.noContent().build()
        }

        val response: List<PatientListResponse> = patientList.map {

            val latestVisit = if (it.visitList.isEmpty()) {
                null
            } else {
                it.visitList.maxOf { visit -> visit.receptionDateTime }
            }

            PatientListResponse(
                it.name,
                it.registrationNumber,
                it.sexCode.codeName,
                it.birthDate,
                it.phoneNumber,
                latestVisit?.toLocalDate(),
            )
        }

        return ResponseEntity.ok(PageImpl(response, pageRequest.of(), pagedPatientList.totalElements))
    }

    @GetMapping("/api/hospital/{hospitalId}/patient/{patientId}")
    fun findById(@PathVariable hospitalId: Long, @PathVariable patientId: Long): ResponseEntity<PatientResponseDto> {
        val patient = patientService.findById(patientId)
            ?: return ResponseEntity.noContent().build()

        return if (patient.hospital.id == hospitalId) {
            ResponseEntity.ok(patient.toPatientResponseDto())
        } else {
            return ResponseEntity.badRequest().build()
        }
    }

    @DeleteMapping("/api/hospital/{hospitalId}/patient/{patientId}")
    fun delete(@PathVariable hospitalId: Long, @PathVariable patientId: Long): ResponseEntity<Long> {
        val patient = patientService.findById(patientId)
            ?: return ResponseEntity.noContent().build()

        return if (patient.hospital.id == hospitalId) {
            patientService.delete(patient)
            ResponseEntity.ok(patientId)
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    fun Patient.toPatientResponseDto(): PatientResponseDto =
        PatientResponseDto(
            this.id,
            this.name,
            this.registrationNumber,
            this.sexCode.codeName,
            this.birthDate.toString(),
            this.phoneNumber,
            this.visitList.map { VisitResponseDto(it.receptionDateTime, it.statusCode) }
        )
}
