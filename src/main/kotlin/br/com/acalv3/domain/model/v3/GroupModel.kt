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

@Entity(name = "group_model")
class GroupModel (

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "`seq_group`"
    )
    override var id: Long? = null,

    @Column(nullable = false, unique = true)
    var name: String? = "",

    @Column(nullable = false)
    var monetaryValue: Double? = 0.00,

    @ManyToOne(optional = false, cascade = [CascadeType.PERSIST])
    var category: CategoryModel? = null,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    override var createdAt: LocalDateTime? = null,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    override var lastModifiedAt: LocalDateTime? = null,

) : AbstractModel