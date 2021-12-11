package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.model.v3.CategoryModel
import br.com.acalv3.domain.repository.v3.CategoryRepository
import br.com.acalv3.domain.service.AppService
import org.springframework.stereotype.Service

@Service
class CategoryService(
	val categoryRepository: CategoryRepository,
): AppService<CategoryModel>(categoryRepository) {

    override fun findByName(name: String): CategoryModel =
        categoryRepository.findByName(name)
}