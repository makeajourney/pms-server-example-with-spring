package kr.makeajourney.pms.domain.hospital

import kr.makeajourney.pms.domain.patient.Patient
import kr.makeajourney.pms.domain.visit.Visit
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany


@Entity
data class Hospital(

    @Column(length = 45)
    val name: String,

    @Column(length = 20)
    val nursingInstitutionNumber: String,

    @Column(length = 10)
    val chiefName: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    val patientList: MutableList<Patient> = mutableListOf()

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    val visitList: MutableList<Visit> = mutableListOf()

    fun addPatient(patient: Patient) {
        this.patientList.add(patient)
    }

    fun addVisit(visit: Visit) {
        this.visitList.add(visit)
    }
}
