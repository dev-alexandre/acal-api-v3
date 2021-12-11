package br.com.acalv3.domain.gateway

import br.com.acalv3.domain.gateway.v3.CategoryGateway
import br.com.acalv3.domain.service.v3.CategoryService
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.Assert.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
@ExtendWith(MockKExtension::class)
class CategoryGatewayTest {

	@MockK
	lateinit var categoryService: CategoryService

	@InjectMockKs
	lateinit var categoryGateway: CategoryGateway

	@Test
	fun `Should be initialize`(){
		assertNotNull(categoryGateway)
	}

}