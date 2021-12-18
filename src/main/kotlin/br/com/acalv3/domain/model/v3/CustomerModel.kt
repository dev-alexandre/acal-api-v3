package br.com.acalv3.domain.model.v3

import br.com.acalv3.domain.model.AbstractModel
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

const val SEQ_NAME = "seq_customer"

@Entity(name = "customer_model")
class CustomerModel (

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = SEQ_NAME
    )
    override var id: Long? = null,

    @Column(nullable = false)
    var name: String? = "",

    @Column(nullable = false, unique = true)
    var document: String? = "",

    var businessName: String? = "",

    var phoneNumber: String? = "",

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    var birthDate: LocalDate? = null,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    override var createdAt: LocalDateTime? = null,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    override var lastModifiedAt: LocalDateTime? = null,

) : AbstractModel