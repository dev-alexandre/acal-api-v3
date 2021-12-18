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

@Entity(name = "place_model")
class PlaceModel(

	@Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "`seq_place`"
    )
	override var id: Long? = null,

	@ManyToOne(cascade = [CascadeType.MERGE])
    var address: AddressModel? = null,

	@Column(nullable = false)
	var number: String? = "",

	var letter: String? = "",

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	override var createdAt: LocalDateTime? = null,

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	override var lastModifiedAt: LocalDateTime? = null,

) : AbstractModel