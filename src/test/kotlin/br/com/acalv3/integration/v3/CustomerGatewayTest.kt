package br.com.acalv3.integration.v3

import br.com.acalv3.AppAdvice
import br.com.acalv3.acalapiv3.builder.v3.CustomerDataBuilder
import br.com.acalv3.domain.gateway.v3.CustomerGateway
import br.com.acalv3.domain.model.v3.CustomerModel
import br.com.acalv3.domain.service.v3.CustomerService
import br.com.acalv3.integration.AbstractGatewayTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class CustomerGatewayTest: AbstractGatewayTest<CustomerModel>() {

	override fun getUrl() = "/customer"

	lateinit var mckMvc: MockMvc

	@Autowired
	lateinit var service: CustomerService

	override fun getMockMvcInstance(): MockMvc {
		if(!this::mckMvc.isInitialized){
			mckMvc = MockMvcBuilders
				.standaloneSetup(CustomerGateway(service))
				.setControllerAdvice(AppAdvice())
				.build()
		}

		return mckMvc
	}

	override fun getModel() = CustomerDataBuilder.build{}
	override fun getClassType() = CustomerModel::class.java

}