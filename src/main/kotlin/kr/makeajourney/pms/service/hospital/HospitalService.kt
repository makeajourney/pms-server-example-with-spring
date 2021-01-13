package kr.makeajourney.pms.service.hospital

import kr.makeajourney.pms.domain.hospital.Hospital
import kr.makeajourney.pms.domain.hospital.HospitalRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class HospitalService(val hospitalRepository: HospitalRepository) {

    fun findById(id: Long): Hospital? {
        return hospitalRepository.findByIdOrNull(id)
    }
}
