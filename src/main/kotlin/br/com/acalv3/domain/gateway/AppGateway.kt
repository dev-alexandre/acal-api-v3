package br.com.acalv3.domain.gateway

import br.com.acalv3.domain.model.AbstractModel
import br.com.acalv3.domain.service.AppService
import org.springframework.web.bind.annotation.*

@RestController
abstract class AppGateway<U: AbstractModel> (
    private val appService: AppService<U>
    ) {

    @PostMapping
    fun save(@RequestBody u: U) = run {
        appService.save(u)
    }

    @PutMapping
    fun update(@RequestBody u: U) =
        appService.update(u)

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): U =
        appService.get(id)

    @GetMapping
    fun getAll(): List<U> =
        appService.getAll()

    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name: String): U =
        appService.findByName(name)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        appService.delete(id)
}