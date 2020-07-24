package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.issue.RequestIssueDto
import io.github.bsfranca2.athena.service.ProductBacklogService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/product-backlogs")
class ProductBacklogController(private val productBacklogService: ProductBacklogService) {

    @GetMapping("/{productBacklogId}")
    fun getProductBacklog(@PathVariable productBacklogId: Long)
            = productBacklogService.getProductBacklog(productBacklogId)

    @PostMapping("/{productBacklogId}/items")
    fun createProductBacklogItem(@PathVariable productBacklogId: Long, @Valid @RequestBody requestIssueDto: RequestIssueDto)
             = productBacklogService.createProductBacklogItem(productBacklogId, requestIssueDto)

}