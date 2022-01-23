package br.com.acalv3.integration

import br.com.acalv3.domain.model.AbstractModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
abstract class AbstractGatewayTest  <U: AbstractModel>: AbstractTest<U>()  {

	abstract override fun getMockMvcInstance() : MockMvc
	abstract override fun getUrl(): String
	abstract override fun getModel(): U
	abstract override fun getClassType(): Class<U>

	@Test
	fun `Should Save`() {
		val saved = save()

		assertNotNull(saved.id )
		assertNotNull(saved.name)

		assertNotNull(saved.createdAt)
		assertNotNull(saved.lastModifiedAt)
		assertEquals(saved.deleted, false)
		assertNull(saved.deletedAt)
		assertNull(saved.deletedBy)
	}

	@Test
	fun `Should update`() {
		val original = save()
		val updated = update(original)

		assertEquals(original.id, updated.id)
		assertEquals(original.createdAt, updated.createdAt)
	}

	@Test
	fun `Should get by ID`() {
		val savedObject = save()
		val getterById = getById(savedObject.id!!)

		assertNotNull(getterById.id)
		assertNotNull(getterById.createdAt)
		assertNotNull(getterById.lastModifiedAt)
	}

	@Test
	fun `Should count`()  {
		save()

		val count = count()
		assertEquals(count, "1")
	}

	@Test
	fun `Should get by Name`() {
		save()
		val getterByName = getterByName()
		assertNotNull(getterByName.id)
		assertNotNull(getterByName.createdAt)
		assertNotNull(getterByName.lastModifiedAt)
	}

	fun `Should response with 400 bad request when save without name`(){

		val model = getModel()
		model.name = null

		val response = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.post(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(model))
		)
		.andDo(print())
		.andExpect(status().isBadRequest)
		.andReturn()

		val responseAsMap = castResponseToMap(response.response.contentAsString)

		Assertions.assertEquals(responseAsMap["message"], "Campo nulo")
	}

	@Test
	fun `Should delete`() {
		val saved = save()
		delete(saved)
	}

}
