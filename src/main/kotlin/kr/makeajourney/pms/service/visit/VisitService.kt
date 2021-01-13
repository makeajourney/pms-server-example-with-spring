package kr.makeajourney.pms.service.visit


import kr.makeajourney.pms.domain.patient.Patient
import kr.makeajourney.pms.domain.visit.Visit
import kr.makeajourney.pms.domain.visit.VisitRepository
import kr.makeajourney.pms.web.dto.VisitSaveRequestDto
import kr.makeajourney.pms.web.dto.VisitUpdateRequestDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class VisitService(val visitRepository: VisitRepository) {

    fun save(patient: Patient, requestDto: VisitSaveRequestDto): Long {
        return visitRepository.save(requestDto.toEntity(patient)).id
    }

    @Transactional
    fun update(visit: Visit, requestDto: VisitUpdateRequestDto): Long {
        visit.update(
            requestDto.receptionDateTime,
            requestDto.statusCode,
        )
        return visit.id
    }

    fun findById(visitId: Long): Visit? {
        return visitRepository.findByIdOrNull(visitId)
    }

    @Transactional
    fun delete(visit: Visit) {
        visitRepository.delete(visit)
    }
}
