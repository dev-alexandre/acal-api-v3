package br.com.acalv3.domain.gateway.v3

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthGateway {

    @GetMapping("/status")
    fun status(): String {
        return "ok"
    }

}