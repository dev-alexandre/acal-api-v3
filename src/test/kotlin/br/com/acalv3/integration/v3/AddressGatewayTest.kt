package br.com.acalv3.integration.v3

import br.com.acalv3.acalapiv3.builder.AddressDataBuilder
import br.com.acalv3.acalapiv3.builder.AddressTypeDataBuilder
import br.com.acalv3.domain.gateway.v3.AddressGateway
import br.com.acalv3.domain.model.v3.AddressModel
import br.com.acalv3.domain.service.v3.AddressService
import br.com.acalv3.integration.AbstractGatewayTest
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class AddressGatewayTest: AbstractGatewayTest<AddressModel>() {

	override fun getUrl() = "/address"

	lateinit var mckMvc: MockMvc

	@Autowired
	lateinit var service: AddressService

	override fun getMockMvcInstance(): MockMvc {
		if(!this::mckMvc.isInitialized){
			mckMvc = MockMvcBuilders
				.standaloneSetup(AddressGateway(service))
				.setControllerAdvice()
				.build()
		}

		return mckMvc
	}

	override fun getModel() =
		AddressDataBuilder.build{
			id = null
			name = "Fernando Daltro"
			addressType = AddressTypeDataBuilder.build{
				id = null
				name = "Rua"
			}
		}

	override fun getModelName() = "Fernando Daltro"
	override fun getClassType() = AddressModel::class.java
}