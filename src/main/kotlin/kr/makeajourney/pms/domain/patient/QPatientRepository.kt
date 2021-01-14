package kr.makeajourney.pms.domain.patient


import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.makeajourney.pms.domain.patient.QPatient.patient
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository


@Repository
class QPatientRepository(
    val jpaQueryFactory: JPAQueryFactory,
) {

    fun findByHospitalIdAndQuery(
        hospitalId: Long,
        name: String?,
        registrationNo: String?,
        birthDate: String?,
        pageable: Pageable,
    ): Page<Patient> {

        val results = jpaQueryFactory
            .selectFrom(patient)
            .leftJoin(patient.visitList)
            .fetchJoin()
            .where(
                patient.hospital.id.eq(hospitalId),
                nameEq(name),
                registrationNumberEq(registrationNo),
                birthDateEq(birthDate)
            )
            .distinct()
            .limit(pageable.pageSize.toLong())
            .offset(pageable.offset)
            .fetchResults()

        return PageImpl(results.results, pageable, results.total)
    }

    private fun nameEq(name: String?): BooleanExpression? {
        return if (name != null) patient.name.eq(name) else null
    }

    private fun registrationNumberEq(registrationNo: String?): BooleanExpression? {
        return if (registrationNo != null) patient.registrationNumber.eq(registrationNo) else null
    }

    private fun birthDateEq(birthDate: String?): BooleanExpression? {
        return if (birthDate != null) patient.birthDate.eq(birthDate) else null
    }
}
