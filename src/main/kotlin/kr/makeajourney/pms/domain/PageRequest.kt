package kr.makeajourney.pms.domain


data class PageRequest(
    var pageNo: Int = 1,
    var pageSize: Int = 10
) {

    fun of(): org.springframework.data.domain.PageRequest {
        return org.springframework.data.domain.PageRequest.of(pageNo - 1, pageSize)
    }
}
