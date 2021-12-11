package br.com.acalv3.domain.exception

class RequiredFieldException(

    override val message: String

) : RuntimeException(message)