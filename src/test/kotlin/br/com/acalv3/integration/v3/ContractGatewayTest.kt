package br.com.acalv3.integration.v3

import br.com.acalv3.AppAdvice
import br.com.acalv3.acalapiv3.builder.v3.ContractDataBuilder
import br.com.acalv3.domain.gateway.v3.ContractGateway
import br.com.acalv3.domain.model.v3.ContractModel
import br.com.acalv3.domain.service.v3.ContractService
import br.com.acalv3.integration.AbstractGatewayTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class ContractGatewayTest: AbstractGatewayTest<ContractModel>() {

	override fun getUrl() = "/contract"

	lateinit var mckMvc: MockMvc

	@Autowired
	lateinit var service: ContractService

	override fun getMockMvcInstance(): MockMvc {
		if(!this::mckMvc.isInitialized){
			mckMvc = MockMvcBuilders
				.standaloneSetup(ContractGateway(service))
				.setControllerAdvice(AppAdvice())
				.build()
		}

		return mckMvc
	}

	override fun getModel() =
		ContractDataBuilder.build{
			customer = null
			group = null
			placeResidence = null
			placeMail = null
		}

	override fun getClassType() = ContractModel::class.java
}