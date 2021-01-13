package kr.makeajourney.pms.domain.patient

enum class Sex(val codeName: String) {
    M("남"),
    F("여"),
    U("알수없음");

    companion object {
        fun code(codeName: String): Sex {
            return when (codeName) {
                "남" -> M
                "여" -> F
                else -> U
            }
        }
    }
}
