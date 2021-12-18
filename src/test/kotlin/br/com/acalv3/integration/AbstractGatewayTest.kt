package br.com.acalv3.integration

import br.com.acalv3.domain.model.AbstractModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc

abstract class AbstractGatewayTest  <U: AbstractModel>: AbstractTest<U>()  {

	abstract override fun getMockMvcInstance() : MockMvc
	abstract override fun getUrl(): String
	abstract override fun getModel(): U
	abstract override fun getClassType(): Class<U>

	@Test
	fun `Should Save and add return abstract fields`() {
		val saved = save()

		assertNotNull(saved.id)
		assertNotNull(saved.createdAt)
		assertNotNull(saved.lastModifiedAt)
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
		val saved = save()



		/*
		val result = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.get("${getUrl()}/name/${getModelName()}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		)
			.andExpect(status().isOk)
			.andReturn()

		castToAbstractModel(result = result)
		 */
	}



	/*
	fun `Should throws when abstractModel is duplicate `() {

		val andReturn = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.put(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getModel()))
		)
		.andDo(print())
	}





	fun `Should delete`() {
		getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.delete("${getUrl()}/1")
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk)
		.andReturn()
	}

	private fun getByName(): AbstractModel {
		val byName = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.get("${getUrl()}/name/${getModelName()}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		)
		.andReturn()

		return objectMapper.readValue(byName.response.contentAsString, getClassType())
	}


	private fun validate(model: U): U{
		assertNotNull(model.id)
		assertNotNull(model.createdAt)
		assertNotNull(model.lastModifiedAt)
		return model
	}
	*/

}
