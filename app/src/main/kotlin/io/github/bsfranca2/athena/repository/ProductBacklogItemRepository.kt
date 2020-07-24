package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.scrum.ProductBacklogItem
import org.springframework.data.repository.CrudRepository

interface ProductBacklogItemRepository : CrudRepository<ProductBacklogItem, Long>