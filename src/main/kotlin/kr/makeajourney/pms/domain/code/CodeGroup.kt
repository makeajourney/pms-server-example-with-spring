package kr.makeajourney.pms.domain.code

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany


@Entity
data class CodeGroup(

    @Id
    @Column(length = 10)
    val name: String,

    @Column(length = 30)
    val description: String,
) {

    @OneToMany(mappedBy = "codeGroup")
    val codeList: List<Code> = arrayListOf()
}
