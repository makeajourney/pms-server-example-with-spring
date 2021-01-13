package kr.makeajourney.pms.domain.patient

import kr.makeajourney.pms.domain.hospital.Hospital
import kr.makeajourney.pms.domain.visit.Visit
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany


@Entity
data class Patient(

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    val hospital: Hospital,

    @Column(length = 45)
    var name: String,

    @Column(length = 13)
    var registrationNumber: String,

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    var sexCode: Sex,

    @Column(length = 10)
    var birthDate: String? = null,

    @Column(length = 20)
    var phoneNumber: String? = null,
) {
    init {
        hospital.addPatient(this)
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @OneToMany(mappedBy = "patient")
    val visitList: MutableList<Visit> = mutableListOf()

    fun update(name: String, registrationNumber: String, sex: String, birthDate: String, phoneNumber: String) {
        this.name = name
        this.registrationNumber = registrationNumber
        this.sexCode = Sex.code(sex)
        this.birthDate = birthDate
        this.phoneNumber = phoneNumber
    }

    fun addVisit(visit: Visit) {
        this.visitList.add(visit)
    }
}
