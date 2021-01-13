package kr.makeajourney.pms.domain.code

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Entity
data class Code(

    @ManyToOne
    @JoinColumn(name = "code_group_name")
    val codeGroup: CodeGroup,

    @Id
    @Column(length = 10)
    val code: String,

    @Column(length = 10)
    val codeName: String,
)
