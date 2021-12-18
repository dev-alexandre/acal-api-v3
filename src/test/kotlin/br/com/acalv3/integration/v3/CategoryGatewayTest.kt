package br.com.acalv3.integration.v3

import br.com.acalv3.AppAdvice
import br.com.acalv3.acalapiv3.builder.v3.CategoryDataBuilder
import br.com.acalv3.domain.gateway.v3.CategoryGateway
import br.com.acalv3.domain.model.v3.CategoryModel
import br.com.acalv3.domain.service.v3.CategoryService
import br.com.acalv3.integration.AbstractGatewayTest
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class CategoryGatewayTest : AbstractGatewayTest<CategoryModel>() {

	override fun getUrl() = "/category"

	lateinit var mckMvc: MockMvc

	@Autowired
	lateinit var service: CategoryService

	override fun getMockMvcInstance(): MockMvc {
		if(!this::mckMvc.isInitialized){
			mckMvc = MockMvcBuilders
				.standaloneSetup(CategoryGateway(service))
				.setControllerAdvice(AppAdvice())
				.build()
		}

		return mckMvc
	}

	override fun getModel() = CategoryDataBuilder.build{}
	override fun getClassType() = CategoryModel::class.java

	fun `Should response with 400 bad request when save without name`(){

		val addressTypeWithoutName = CategoryDataBuilder.build {
			name = null
		}

		val response = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.post(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(addressTypeWithoutName))
		)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isBadRequest)
		.andReturn()

		val responseAsMap = castResponseToMap(response.response.contentAsString)
		Assertions.assertEquals(responseAsMap["message"], "Campo nulo")
	}
}