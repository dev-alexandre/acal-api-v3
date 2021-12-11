package br.com.acalv3.domain.gateway.v3

import br.com.acalv3.domain.gateway.AppGateway
import br.com.acalv3.domain.model.v3.CategoryModel
import br.com.acalv3.domain.service.v3.CategoryService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("category")
class CategoryGateway(
    categoryService: CategoryService
): AppGateway<CategoryModel>(categoryService)