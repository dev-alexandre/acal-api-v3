package br.com.acalv3.domain.service

import br.com.acalv3.acalapiv3.builder.AddressDataBuilder
import br.com.acalv3.acalapiv3.builder.AddressTypeDataBuilder
import br.com.acalv3.domain.model.v3.AddressModel
import br.com.acalv3.domain.service.v3.AddressService
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
@ExtendWith(SpringExtension::class)
@DataJpaTest
class AddressServiceTest {

	@Autowired
	lateinit var addressService: AddressService

	@Test
	fun `Should save cascaded object`() {
		val address = addressService.save(saveAddress())

		assertNotNull(address.id)
		assertNotNull(address.addressType?.id)
	}

	fun `Should save with with data createdAt and lastModifiedAt object`() {
		val address = addressService.save(saveAddress())
		assertNotNull(address.createdAt)
		assertNotNull(address.lastModifiedAt)
	}

	@Test
	fun `Should find by name`() {
		val savedAddress = saveAddress()
		val address = addressService.findByName(savedAddress.name!!)

		assertNotNull(address)
	}

	private fun saveAddress(): AddressModel {

		val count = addressService.count()

		val addressModel = AddressDataBuilder.build {
			id = null
			name = "addressName_$count"

			addressType = AddressTypeDataBuilder.build {
				id = null
				name = "addressTypeName_$count"
			}
		}

		return addressService.save(addressModel)
	}
}


