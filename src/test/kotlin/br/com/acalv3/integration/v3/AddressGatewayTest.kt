package br.com.acalv3.integration.v3

import br.com.acalv3.AppAdvice
import br.com.acalv3.acalapiv3.builder.v3.AddressDataBuilder
import br.com.acalv3.domain.gateway.v3.AddressGateway
import br.com.acalv3.domain.model.v3.AddressModel
import br.com.acalv3.domain.service.v3.AddressService
import br.com.acalv3.integration.AbstractGatewayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class AddressGatewayTest: AbstractGatewayTest<AddressModel>() {

	override fun getUrl() = "/address"

	lateinit var mckMvc: MockMvc

	@Autowired
	lateinit var service: AddressService

	override fun getMockMvcInstance(): MockMvc {
		if(!this::mckMvc.isInitialized){
			mckMvc = MockMvcBuilders
				.standaloneSetup(AddressGateway(service))
				.setControllerAdvice(AppAdvice())
				.build()
		}

		return mckMvc
	}

	override fun getModel() = AddressDataBuilder.build{}
	override fun getClassType() = AddressModel::class.java

	@Test
	fun `Should throws when save without name`(){
		val addressWithoutName = AddressDataBuilder.build {
			name = null
		}

		val response = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.post(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(addressWithoutName))
		)
		.andDo(print())
		.andExpect(status().isBadRequest)
		.andReturn()

		val responseAsMap = castResponseToMap(response.response.contentAsString)

		Assertions.assertEquals(responseAsMap["message"], "Campo nulo")
	}

	@Test
	fun `Should response with 400 bad request when save without type`(){
		val addressWithoutName = AddressDataBuilder.build {
			addressType = null
		}

		val response = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.post(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(addressWithoutName))
		)
			.andDo(print())
			.andExpect(status().isBadRequest)
			.andReturn()

		val responseAsMap = castResponseToMap(response.response.contentAsString)

		Assertions.assertEquals(responseAsMap["message"], "Campo nulo")
	}
}