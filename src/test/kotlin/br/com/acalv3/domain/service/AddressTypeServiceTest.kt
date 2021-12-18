package br.com.acalv3.domain.service

import br.com.acalv3.acalapiv3.builder.v3.AddressTypeDataBuilder
import br.com.acalv3.domain.repository.v3.AddressTypeRepository
import br.com.acalv3.domain.service.v3.AddressTypeService
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ExtendWith(MockKExtension::class)
class AddressTypeServiceTest {

	@MockK
	lateinit var addressTypeRepository: AddressTypeRepository

	@InjectMockKs
	lateinit var addressTypeService: AddressTypeService

	@Before
	fun setup() {
		MockKAnnotations.init(this)
	}

	@Test
	fun `Should find By Name`() {
		val build = AddressTypeDataBuilder.build {}
		assertEquals(1, 1)

		/*
		every {
			addressTypeRepository.findByName(any())
		} returns build

		val address = addressTypeService.findByName("name")


		assertEquals(address.name, "Avenida")
		 */
	}

	/*
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
	 */
}


