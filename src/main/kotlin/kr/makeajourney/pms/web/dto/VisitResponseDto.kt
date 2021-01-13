package kr.makeajourney.pms.web.dto


import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime


data class VisitResponseDto(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val receptionDateTime: LocalDateTime,
    val statusCode: String,
)
