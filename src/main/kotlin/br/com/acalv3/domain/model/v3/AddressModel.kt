package br.com.acalv3.domain.model.v3

import br.com.acalv3.domain.model.AbstractModel
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity(name = "address_model")
class AddressModel (

	@Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "`seq_address`"
    )
    override var id: Long? = null,

	@ManyToOne(optional = false, cascade = [CascadeType.PERSIST])
    var addressType: AddressTypeModel? = null,

	@Column(nullable = false, unique = true)
    var name: String? = "",

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	override var createdAt: LocalDateTime? = null,

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	override var lastModifiedAt: LocalDateTime? = null

) : AbstractModel