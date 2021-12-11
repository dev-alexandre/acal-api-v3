package br.com.acalv3.integration

import br.com.acalv3.acalapiv3.builder.AddressTypeDataBuilder
import br.com.acalv3.domain.gateway.v3.AddressTypeGateway
import br.com.acalv3.domain.service.v3.AddressTypeService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//@WebMvcTest(HealthGateway::class)
internal class AddressTypeGatewayTest {

	companion object{
		const val URL = "/address-type"
	}

	private lateinit var mockMvc: MockMvc

	@Autowired
	lateinit var addressTypeService: AddressTypeService

	@BeforeEach
	fun setup() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(AddressTypeGateway(addressTypeService))
			.setControllerAdvice()
			.build()
	}

	@Test
	fun `when Valid Input then Returns 200`() {
		val addressTypeModel = AddressTypeDataBuilder.build()

		val result = mockMvc.perform(
			MockMvcRequestBuilders
				.get("$URL/get/${addressTypeModel.id}")
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk).andReturn()

		assertEquals(result.response.contentAsString, "ok")
	}

}