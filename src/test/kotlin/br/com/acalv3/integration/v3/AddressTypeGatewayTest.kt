package br.com.acalv3.integration.v3

import br.com.acalv3.acalapiv3.builder.AddressTypeDataBuilder
import br.com.acalv3.domain.gateway.v3.AddressTypeGateway
import br.com.acalv3.domain.model.v3.AddressTypeModel
import br.com.acalv3.domain.service.v3.AddressTypeService
import br.com.acalv3.integration.AbstractGatewayTest
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class AddressTypeGatewayTest: AbstractGatewayTest<AddressTypeModel>() {

	override fun getUrl() = "/address-type"

	lateinit var mckMvc: MockMvc

	@Autowired
	lateinit var service: AddressTypeService

	override fun getMockMvcInstance(): MockMvc {
		if(!this::mckMvc.isInitialized){
			mckMvc = MockMvcBuilders
				.standaloneSetup(AddressTypeGateway(service))
				.setControllerAdvice()
				.build()
		}

		return mckMvc
	}

	override fun getModel() =
		AddressTypeDataBuilder.build{
			id = null
			name = "Avenida"
		}

	override fun getModelName() = "Avenida"
	override fun getClassType() = AddressTypeModel::class.java


}