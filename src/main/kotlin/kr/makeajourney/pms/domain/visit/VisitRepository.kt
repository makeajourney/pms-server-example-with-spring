package kr.makeajourney.pms.domain.visit

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface VisitRepository : JpaRepository<Visit, Long> {
}
