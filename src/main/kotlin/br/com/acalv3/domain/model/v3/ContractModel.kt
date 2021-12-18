package br.com.acalv3.domain.model.v3

import br.com.acalv3.domain.model.AbstractModel
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity(name = "contract_model")
class ContractModel (

	@Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "`seq_contract`"
    )
    override var id: Long? = null,

	override var name: String? = "",

	var nameBusiness: String? = "",

	@ManyToOne(cascade = [CascadeType.PERSIST])
	var group: GroupModel? = null,

	@ManyToOne(cascade = [CascadeType.PERSIST])
    var addressResidence: AddressModel? = null,

	@ManyToOne(cascade = [CascadeType.PERSIST])
	var addressMail: AddressModel? = null,

	@ManyToOne(cascade = [CascadeType.PERSIST])
	var customer: CustomerModel? = null,

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	override var createdAt: LocalDateTime? = null,

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	override var lastModifiedAt: LocalDateTime? = null

) : AbstractModel