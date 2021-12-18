package br.com.acalv3.integration.v3

import br.com.acalv3.AppAdvice
import br.com.acalv3.acalapiv3.builder.v3.CustomerDataBuilder
import br.com.acalv3.domain.gateway.v3.CustomerGateway
import br.com.acalv3.domain.model.v3.CustomerModel
import br.com.acalv3.domain.service.v3.CustomerService
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CustomerGatewayTest: AbstractGatewayTest<CustomerModel>() {

	override fun getUrl() = "/customer"

	lateinit var mckMvc: MockMvc

	@Autowired
	lateinit var service: CustomerService

	override fun getMockMvcInstance(): MockMvc {
		if(!this::mckMvc.isInitialized){
			mckMvc = MockMvcBuilders
				.standaloneSetup(CustomerGateway(service))
				.setControllerAdvice(AppAdvice())
				.build()
		}

		return mckMvc
	}

	override fun getModel() = CustomerDataBuilder.build{}
	override fun getClassType() = CustomerModel::class.java

	@Test
	fun `Should response with 400 bad request when save without name`(){

		val customer = CustomerDataBuilder.build {
			name = null
		}

		val response = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.post(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer))
		)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isBadRequest)
			.andReturn()

		val responseAsMap = castResponseToMap(response.response.contentAsString)
		Assertions.assertEquals(responseAsMap["message"], "Campo nulo")
	}

	@Test
	fun `Should response with 400 bad request when save without document`(){

		val customer = CustomerDataBuilder.build {
			document = null
		}

		val response = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.post(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer))
		)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isBadRequest)
			.andReturn()

		val responseAsMap = castResponseToMap(response.response.contentAsString)
		Assertions.assertEquals(responseAsMap["message"], "Campo nulo")
	}
}