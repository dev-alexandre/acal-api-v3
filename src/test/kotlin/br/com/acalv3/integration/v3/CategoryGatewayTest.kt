package br.com.acalv3.integration.v3

import br.com.acalv3.AppAdvice
import br.com.acalv3.acalapiv3.builder.v3.CategoryDataBuilder
import br.com.acalv3.domain.gateway.v3.CategoryGateway
import br.com.acalv3.domain.model.v3.CategoryModel
import br.com.acalv3.domain.service.v3.CategoryService
import br.com.acalv3.integration.AbstractGatewayTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
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
}