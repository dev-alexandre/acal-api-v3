package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.AbstractModel
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

abstract class AppService<U: AbstractModel>(
    private val appRepository: JpaRepository<U, Long>
) {
    fun delete(id: Long) {
        if(!appRepository.existsById(id)){
            throw NoSuchElementException("Entity not found")
        }
        appRepository.deleteById(id)
    }

    fun update(u: U) : U =
        save(u)

    fun save(u: U) : U {
        valid(u)
        prepareForSave(u)
        return appRepository.save(u)
    }

    fun get(id: Long): U =
        appRepository.findById(id).orElseThrow {
            NoSuchElementException("Entity not found")
        }

    fun getAll(): List<U> =
        appRepository.findAll()

    fun count(): Long =
        appRepository.count()

    abstract fun findByName(name: String): U

    open fun valid(u: U) = Unit

    open fun prepareForSave(u: U) {

        if (u.createdAt == null) {
            u.createdAt = LocalDateTime.now()
        }

        if (u.lastModifiedAt == null) {
            u.lastModifiedAt = LocalDateTime.now()
        }

    }
}

