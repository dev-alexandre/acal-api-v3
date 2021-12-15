package br.com.acalv3.integration.v3

import br.com.acalv3.acalapiv3.builder.CategoryDataBuilder
import br.com.acalv3.domain.gateway.v3.CategoryGateway
import br.com.acalv3.domain.model.v3.CategoryModel
import br.com.acalv3.domain.service.v3.CategoryService
import br.com.acalv3.integration.AbstractGatewayTest
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CategoryGatewayTest : AbstractGatewayTest<CategoryModel>() {

	override fun getUrl() = "/category"

	lateinit var mckMvc: MockMvc

	@Autowired
	lateinit var service: CategoryService

	override fun getMockMvcInstance(): MockMvc {
		if(!this::mckMvc.isInitialized){
			mckMvc = MockMvcBuilders
				.standaloneSetup(CategoryGateway(service))
				.setControllerAdvice()
				.build()
		}

		return mckMvc
	}

	override fun getModel() =
		CategoryDataBuilder.build{
			id = null
			name = "Fundador"
		}

	override fun getModelName() = "Fundador"
	override fun getClassType() = CategoryModel::class.java

}