package br.com.acalv3.integration

import br.com.acalv3.domain.model.AbstractModel
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

abstract class AbstractGatewayTest<U: AbstractModel> {

	abstract fun getMockMvcInstance() : MockMvc
	abstract fun getUrl(): String
	abstract fun getModel(): U
	abstract fun getModelName(): String
	abstract fun getClassType(): Class<U>

	@Autowired
	open lateinit var objectMapper: ObjectMapper

	@Test
	@Order(1)
	fun `Should Save and add Abstract Gateway fields`() {

		val result = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.post(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getModel()))
		)
		.andDo(print())
		.andExpect(status().isOk)
		.andReturn()

		castToAbstractModel(result = result)
	}

	@Test
	@Order(2)
	fun `Should throws when abstractModel is duplicate `() {

		val andReturn = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.put(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getModel()))
		)
		.andDo(print())
		//.andExpect(res -> {})
		//.andExpect(result -> assertTrue(result.getResolvedException() instanceof WhateverException))
		//.andReturn()

		/*
		val someException: Optional<SomeException> = Optional.ofNullable(result.getResolvedException() as SomeException)

		someException.ifPresent { se -> assertThat(se, `is`(notNullValue())) }
		someException.ifPresent { se -> assertThat(se, `is`(instanceOf(SomeException::class.java))) }
		 */
	}

	fun `Should get by ID`() {
		val result = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.get("${getUrl()}/${getByName().id}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk)
		.andReturn()

		castToAbstractModel(result = result)
	}

	fun `Should get by Name`() {
		val result = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.get("${getUrl()}/name/${getModelName()}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk)
		.andReturn()

		castToAbstractModel(result = result)
	}

	fun `Should update`() {
		val model = getByName()

		val result = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.put(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(model))
		)
		.andExpect(status().isOk)
		.andReturn()

		 val modelUpdated = castToAbstractModel(result = result)
		 assertNotEquals(modelUpdated.lastModifiedAt, model.lastModifiedAt)
	}

	fun `Should count`()  {
		val result = getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.get("${getUrl()}/count")
		)
		.andExpect(status().isOk)
		.andReturn()
	}

	fun `Should list`() {
		val result =getMockMvcInstance().perform(
			MockMvcRequestBuilders
				.get(getUrl())
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk)
			.andReturn()
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

	private fun castToAbstractModel (result: MvcResult): AbstractModel {
		val model = objectMapper.readValue(result.response.contentAsString, getClassType())
		return validate(model = model)
	}

	private fun validate(model: AbstractModel): AbstractModel{
		assertNotNull(model.id)
		assertNotNull(model.createdAt)
		assertNotNull(model.lastModifiedAt)
		return model
	}

}
