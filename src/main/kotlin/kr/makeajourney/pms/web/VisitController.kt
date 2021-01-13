package kr.makeajourney.pms.web


import kr.makeajourney.pms.domain.visit.Visit
import kr.makeajourney.pms.service.patient.PatientService
import kr.makeajourney.pms.service.visit.VisitService
import kr.makeajourney.pms.web.dto.VisitResponseDto
import kr.makeajourney.pms.web.dto.VisitSaveRequestDto
import kr.makeajourney.pms.web.dto.VisitUpdateRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class VisitController(
    val visitService: VisitService,
    val patientService: PatientService,
) {

    @PostMapping("/api/hospital/{hospitalId}/patient/{patientId}/visit")
    fun save(
        @PathVariable hospitalId: Long,
        @PathVariable patientId: Long,
        @RequestBody requestDto: VisitSaveRequestDto
    ): ResponseEntity<Long> {

        val patient = patientService.findById(patientId)
            ?: return ResponseEntity.badRequest().build()

        return if (patient.hospital.id == hospitalId) {
            ResponseEntity.ok(visitService.save(patient, requestDto))
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/api/hospital/{hospitalId}/patient/{patientId}/visit/{visitId}")
    fun update(
        @PathVariable hospitalId: Long,
        @PathVariable patientId: Long,
        @PathVariable visitId: Long,
        @RequestBody requestDto: VisitUpdateRequestDto,
    ): ResponseEntity<Long> {

        val visit = visitService.findById(visitId)
            ?: return ResponseEntity.noContent().build()

        return if ((visit.hospital.id == hospitalId) and (visit.patient.id == patientId)) {
            ResponseEntity.ok(visitService.update(visit, requestDto))
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/api/hospital/{hospitalId}/patient/{patientId}/visit/{visitId}")
    fun findById(
        @PathVariable hospitalId: Long,
        @PathVariable patientId: Long,
        @PathVariable visitId: Long,
    ): ResponseEntity<VisitResponseDto> {

        val visit = visitService.findById(visitId)
            ?: return ResponseEntity.noContent().build()

        return if ((visit.hospital.id == hospitalId) and (visit.patient.id == patientId)) {
            ResponseEntity.ok(visit.toVisitResponseDto())
        } else {
            return ResponseEntity.badRequest().build()
        }
    }

    @DeleteMapping("/api/hospital/{hospitalId}/patient/{patientId}/visit/{visitId}")
    fun delete(
        @PathVariable hospitalId: Long,
        @PathVariable patientId: Long,
        @PathVariable visitId: Long,
    ): ResponseEntity<Long> {
        
        val visit = visitService.findById(visitId)
            ?: return ResponseEntity.noContent().build()

        return if ((visit.hospital.id == hospitalId) and (visit.patient.id == patientId)) {
            visitService.delete(visit)
            ResponseEntity.ok(visitId)
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    fun Visit.toVisitResponseDto(): VisitResponseDto {
        return VisitResponseDto(this.receptionDateTime, this.statusCode)
    }
}
