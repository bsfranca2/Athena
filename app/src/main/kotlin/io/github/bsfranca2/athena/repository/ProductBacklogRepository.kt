package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.scrum.ProductBacklog
import org.springframework.data.repository.CrudRepository


interface ProductBacklogRepository : CrudRepository<ProductBacklog, Long>