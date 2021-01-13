package kr.makeajourney.pms.domain.visit

import kr.makeajourney.pms.domain.hospital.Hospital
import kr.makeajourney.pms.domain.patient.Patient
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Visit(

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    val hospital: Hospital,

    @ManyToOne
    @JoinColumn(name = "patient_id")
    val patient: Patient,

    val receptionDateTime: LocalDateTime,
    var statusCode: String,
) {

    init {
        this.hospital.addVisit(this)
        this.patient.addVisit(this)
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
