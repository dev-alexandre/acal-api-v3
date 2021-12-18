package br.com.acalv3.integration.v3

import br.com.acalv3.AppAdvice
import br.com.acalv3.acalapiv3.builder.v3.AddressTypeDataBuilder
import br.com.acalv3.domain.gateway.v3.AddressTypeGateway
import br.com.acalv3.domain.model.v3.AddressTypeModel
import br.com.acalv3.domain.service.v3.AddressTypeService
import br.com.acalv3.integration.AbstractGatewayTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class AddressTypeGatewayTest: AbstractGatewayTest<AddressTypeModel>() {

	override fun getUrl() = "/address-type"

	lateinit var mckMvc: MockMvc

	@Autowired
	lateinit var service: AddressTypeService

	override fun getMockMvcInstance(): MockMvc {
		if(!this::mckMvc.isInitialized){
			mckMvc = MockMvcBuilders
				.standaloneSetup(AddressTypeGateway(service))
				.setControllerAdvice(AppAdvice())
				.build()
		}

		return mckMvc
	}

	override fun getModel() = AddressTypeDataBuilder.build{}
	override fun getClassType() = AddressTypeModel::class.java

	fun `Should response with 400 bad request when save without name`(){

		val addressTypeWithoutName = AddressTypeDataBuilder.build {
			name = null
		}

		val response = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.post(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(addressTypeWithoutName))
		)
		.andDo(print())
		.andExpect(status().isBadRequest)
		.andReturn()

		val responseAsMap = castResponseToMap(response.response.contentAsString)

		assertEquals(responseAsMap["message"],"Campo nulo" )
	}

}