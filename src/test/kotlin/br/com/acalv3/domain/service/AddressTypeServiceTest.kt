package br.com.acalv3.domain.service

import br.com.acalv3.acalapiv3.builder.AddressTypeDataBuilder
import br.com.acalv3.domain.model.v3.AddressTypeModel
import br.com.acalv3.domain.service.v3.AddressTypeService
import org.junit.jupiter.api.Assertions
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
class AddressTypeServiceTest {

	@Autowired
	lateinit var addressTypeService: AddressTypeService

	@Test
	fun `Should save cascaded object`() {
		val address = addressTypeService.save(saveTypeAddress())

		Assertions.assertNotNull(address.id)
	}

	@Test
	fun `Should find by name`() {

		val savedAddressType = saveTypeAddress()
		val addressType = addressTypeService.findByName(savedAddressType.name!!)

		Assertions.assertNotNull(addressType.id)
	}

	private fun saveTypeAddress(): AddressTypeModel {

		val count = addressTypeService.count()

		val addressModel = AddressTypeDataBuilder.build {
			id = null
			name = "addressTypeName_$count"
		}

		return addressTypeService.save(addressModel)
	}
}