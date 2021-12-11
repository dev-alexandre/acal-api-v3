package br.com.acalv3.integration

import br.com.acalv3.domain.gateway.v3.HealthGateway
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringRunner::class)
class HealthGatewayTest {

	private lateinit var mockMvc: MockMvc

	@BeforeEach
	fun setup() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(HealthGateway())
			.setControllerAdvice()
			.build()
	}

	@Test
	fun `when Valid Input then Returns 200`() {
		val result = mockMvc.perform(
			MockMvcRequestBuilders
				.get("/health/status")
				.accept(MediaType.APPLICATION_JSON)
			).andExpect(status().isOk).andReturn()

		assertEquals(result.response.contentAsString, "ok")
	}
}