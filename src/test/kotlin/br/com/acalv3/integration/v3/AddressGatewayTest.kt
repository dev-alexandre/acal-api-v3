package br.com.acalv3.integration.v3

import br.com.acalv3.AppAdvice
import br.com.acalv3.acalapiv3.builder.v3.AddressDataBuilder
import br.com.acalv3.domain.gateway.v3.AddressGateway
import br.com.acalv3.domain.model.v3.AddressModel
import br.com.acalv3.domain.service.v3.AddressService
import br.com.acalv3.integration.AbstractGatewayTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class AddressGatewayTest: AbstractGatewayTest<AddressModel>() {

	override fun getUrl() = "/address"

	lateinit var mckMvc: MockMvc

	@Autowired
	lateinit var service: AddressService

	override fun getMockMvcInstance(): MockMvc {
		if(!this::mckMvc.isInitialized){
			mckMvc = MockMvcBuilders
				.standaloneSetup(AddressGateway(service))
				.setControllerAdvice(AppAdvice())
				.build()
		}

		return mckMvc
	}

	override fun getModel() = AddressDataBuilder.build{}
	override fun getClassType() = AddressModel::class.java

}