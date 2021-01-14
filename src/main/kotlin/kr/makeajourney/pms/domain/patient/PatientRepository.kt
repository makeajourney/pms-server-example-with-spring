package kr.makeajourney.pms.domain.patient

import kr.makeajourney.pms.domain.hospital.Hospital
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface PatientRepository : JpaRepository<Patient, Long> {

    @Query(
        "SELECT p " +
                "FROM Patient p LEFT JOIN FETCH p.visitList " +
                "WHERE p.hospital = :hospital"
    )
    fun getListByHospital(hospital: Hospital): List<Patient>
}
