package br.com.acalv3.domain.service

import br.com.acalv3.domain.repository.v3.AddressTypeRepository
import br.com.acalv3.domain.service.v3.AddressTypeService
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.Before
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

}


