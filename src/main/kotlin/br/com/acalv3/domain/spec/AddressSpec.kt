package br.com.acalv3.domain.spec

import br.com.acalv3.domain.model.v3.AddressModel
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class AddressSpec(
	val addressModel: AddressModel
): Specification<AddressModel>{

	override fun toPredicate(
		root: Root<AddressModel>,
		cq: CriteriaQuery<*>,
		cb: CriteriaBuilder
	): Predicate? {
		val predicates: MutableList<Predicate> = ArrayList()

		if (addressModel.name!!.isNotBlank()) {
			with(predicates) {
				add(
					cb.like(
						cb.lower(root.get("name")),
						addressModel.name?.lowercase() + "%"
					)
				)
			}
		}

		if(addressModel.id != null){
			with(predicates){
				add(
					cb.equal(
						root.get<Int>("id"),
						addressModel.id
					)
				)
			}
		}

		if(addressModel.createdAt != null ) {
			val startingFrom = cb.greaterThanOrEqualTo(
				root.get<LocalDateTime>("createdAt"),
				addressModel.createdAt
			)

			val endingAt =cb.lessThanOrEqualTo(
				root.get<LocalDateTime>("createdAt"),
				addressModel.createdAt
			)

			predicates.add(cb.and(startingFrom, endingAt) )
		}

		if(addressModel.addressType?.id != null ) {

			with(predicates){
				add(
					cb.equal(
						root.get<Int>("addressType").get<Int>("id"), addressModel.addressType?.id
					)
				)
			}
		}

		return andTogether(predicates, cb)
	}

	private fun andTogether(predicates: List<Predicate>, cb: CriteriaBuilder): Predicate? {
		return cb.and(*predicates.toTypedArray())
	}

}