package br.com.acalv3.integration.v3

import br.com.acalv3.AppAdvice
import br.com.acalv3.acalapiv3.builder.v3.GroupDataBuilder
import br.com.acalv3.domain.gateway.v3.GroupGateway
import br.com.acalv3.domain.model.v3.GroupModel
import br.com.acalv3.domain.service.v3.GroupService
import br.com.acalv3.integration.AbstractGatewayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class GroupGatewayTest : AbstractGatewayTest<GroupModel>() {

	override fun getUrl() = "/group"

	lateinit var mckMvc: MockMvc

	@Autowired
	lateinit var service: GroupService

	override fun getMockMvcInstance(): MockMvc {
		if(!this::mckMvc.isInitialized){
			mckMvc = MockMvcBuilders
				.standaloneSetup(GroupGateway(service))
				.setControllerAdvice(AppAdvice())
				.build()
		}

		return mckMvc
	}

	override fun getModel() = GroupDataBuilder.build{}
	override fun getClassType() = GroupModel::class.java

	@Test
	fun `Should response with 400 bad request when save without monetaryValue`(){

		val response = validate(GroupDataBuilder.build {
			monetaryValue = null
		})

		Assertions.assertEquals(response["message"], "Campo nulo")
	}

	@Test
	fun `Should response with 400 bad request when save without category`(){

		val response = validate(GroupDataBuilder.build {
			category = null
		})

		Assertions.assertEquals(response["message"], "Campo nulo")
	}

	private fun validate(group: GroupModel): Map<String, String> {
		val response = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.post(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(group))
		)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isBadRequest)
		.andReturn()

		return castResponseToMap(response.response.contentAsString)
	}

}