package br.com.acalv3.integration.v3

import br.com.acalv3.AppAdvice
import br.com.acalv3.acalapiv3.builder.v3.AddressTypeDataBuilder
import br.com.acalv3.domain.gateway.v3.AddressTypeGateway
import br.com.acalv3.domain.model.v3.AddressTypeModel
import br.com.acalv3.domain.service.v3.AddressTypeService
import br.com.acalv3.integration.AbstractGatewayTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class AddressTypeGatewayTest: AbstractGatewayTest<AddressTypeModel>() {

	@Autowired
	lateinit var service: AddressTypeService
	override fun getUrl() = "/address-type"
	override fun getModel() = AddressTypeDataBuilder.build{}
	override fun getClassType() = AddressTypeModel::class.java

	lateinit var mckMvc: MockMvc

	override fun getMockMvcInstance(): MockMvc {
		if(!this::mckMvc.isInitialized){
			mckMvc = MockMvcBuilders
				.standaloneSetup(AddressTypeGateway(service))
				.setControllerAdvice(AppAdvice())
				.build()
		}

		return mckMvc
	}

	@Test
	fun `Should response with 400 bad request when save duplicated name`(){
		super.save()
		super.trySaveDuplicated()
	}

}