package br.com.acalv3.domain.repository.v3

import br.com.acalv3.domain.model.v3.CategoryModel
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryModel, Long>{
    fun findByName(name: String): CategoryModel
}