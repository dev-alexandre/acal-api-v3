package br.com.acalv3.domain.spec.v3

import br.com.acalv3.domain.model.AbstractModel
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

open class AbstractSpec<U: AbstractModel> (
	private val example: U
): Specification<U> {

	override fun toPredicate(
		root: Root<U?>,
		cq: CriteriaQuery<*>,
		cb: CriteriaBuilder
	): Predicate? {
		val predicates: MutableList<Predicate> = ArrayList()

		if (example.name!!.isNotBlank()) {
			with(predicates) {
				add(
					cb.like(
						cb.lower(root.get("name")),
						example.name?.lowercase() + "%"
					)
				)
			}
		}

		if(example.id != null){
			with(predicates){
				add(
					cb.equal(
						root.get<Int>("id"),
						example.id
					)
				)
			}
		}

		if(example.createdAt != null ) {
			val startingFrom = cb.greaterThanOrEqualTo(
				root.get<LocalDateTime>("createdAt"),
				example.createdAt
			)

			val endingAt =cb.lessThanOrEqualTo(
				root.get<LocalDateTime>("createdAt"),
				example.createdAt
			)

			predicates.add(cb.and(startingFrom, endingAt) )
		}

		return andTogether(predicates, cb)
	}

	private fun andTogether(predicates: List<Predicate>, cb: CriteriaBuilder): Predicate? {
		return cb.and(*predicates.toTypedArray())
	}

}