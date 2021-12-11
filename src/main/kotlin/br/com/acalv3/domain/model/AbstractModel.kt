package br.com.acalv3.domain.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

open class AbstractModel {

    @DateTimeFormat(pattern = PATTERN, iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = PATTERN)
    var createdAt: LocalDateTime? = null

    @DateTimeFormat(pattern = PATTERN, iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = PATTERN)
    var lastModifiedAt: LocalDateTime? = null

    var createdBy: String? = ""
    var lastModifiedBy: String? = ""

    companion object{
        const val PATTERN = "dd_MM_yyyy_HH_mm_ss"
    }
}

