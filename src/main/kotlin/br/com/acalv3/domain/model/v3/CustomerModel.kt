package br.com.acalv3.domain.model.v3

import br.com.acalv3.domain.model.AbstractModel
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.persistence.*

const val SEQ_NAME = "seq_customer"

@Entity(name = "customer_model")
data class CustomerModel (

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = SEQ_NAME
    )
    var id: Long? = null,

    var name: String? = "",

    var businessName: String? = "",

    var document: String? = "",

    var phoneNumber: String? = "",

    var partnerNumber: String? = "",

    var partnerLetter: String? = "",

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    var birthDate: LocalDate? = null

) : AbstractModel()